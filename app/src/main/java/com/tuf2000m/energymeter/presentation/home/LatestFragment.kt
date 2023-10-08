package com.tuf2000m.energymeter.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tuf2000m.energymeter.data.remote.NetworkResult
import com.tuf2000m.energymeter.data.remote.model.meterdata.Timestamp
import com.tuf2000m.energymeter.databinding.FragmentLatestBinding
import com.tuf2000m.energymeter.utils.Constant
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LatestFragment : Fragment() {
    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private var currentIndex = 0
    private var timestamps = mutableListOf<Timestamp>()
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        handleEvents()
    }

    private fun handleEvents() {
        binding.imageView.setOnClickListener {
            if (currentIndex < timestamps.size - 1) {
                currentIndex += 1
                binding.tvTimespam.text =
                    Constant.TimeFormatter.convertUTCFormat(timestamps[currentIndex].timestamp)
                homeAdapter.setContentData(timestamps[currentIndex].data)

            } else {
                Toast.makeText(context, "No More Records", Toast.LENGTH_SHORT).show()
            }
        }
        binding.appCompatEditText.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 0) {
                currentIndex = 0
                homeAdapter.setContentData(timestamps[currentIndex].data)
            } else {
                viewModel.searchData(timestamps, text)
            }
        }
    }

    private fun observers() {
        viewModel.meterdata.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Failure -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    //handleloadinghere
                }

                is NetworkResult.Success -> {
                    it.let {
                        timestamps.addAll(it.data.timestamp)

                        setRecyclerData()

                    }
                }
            }
        }
        viewModel.searchdata.observe(viewLifecycleOwner) {
            it?.let {
                homeAdapter.setContentData(it)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getTimeStamps()
    }

    private fun setRecyclerData() {
        if (timestamps.isNotEmpty()) {
            binding.tvTimespam.text =
                Constant.TimeFormatter.convertUTCFormat(timestamps[currentIndex].timestamp)
            homeAdapter = HomeAdapter(timestamps[currentIndex].data, object : OnItemClickListener {
                override fun onItemClick(position: Int) {

                }
            })
            binding.rvLatest.adapter = homeAdapter
        }
    }

}