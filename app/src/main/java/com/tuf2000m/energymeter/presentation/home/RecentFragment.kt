package com.tuf2000m.energymeter.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tuf2000m.energymeter.data.remote.NetworkResult
import com.tuf2000m.energymeter.data.remote.model.recent.Recents
import com.tuf2000m.energymeter.databinding.FragmentRecentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentFragment : Fragment() {
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var recentAdapter: RecentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.recentdata.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Failure -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    //loadinghere
                }

                is NetworkResult.Success -> {
                    setRecyclerData(it.data.recentList)

                }
            }
        }
    }

    private fun setRecyclerData(recentList: List<Recents.Recent>) {
        if (recentList.isNotEmpty()) {
            recentAdapter = RecentAdapter(recentList, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                }
            })
            binding.rvRecent.adapter = recentAdapter
        }
    }
}