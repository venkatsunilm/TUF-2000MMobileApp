package com.tuf2000m.energymeter.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tuf2000m.energymeter.data.model.recent.Recents
import com.tuf2000m.energymeter.data.remote.NetworkResult
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

        // Observe recent data to update UI accordingly
        observeRecentData()
    }

    /**
     * Observes recent data and updates the UI accordingly.
     */
    private fun observeRecentData() {
        viewModel.recentData.observe(viewLifecycleOwner) { result ->
            when (result) {
                // Handle network failure and loading states if needed...

                is NetworkResult.Success -> {
                    // Set recycler data with the recent list from the result
                    setRecyclerData(result.data.recentList)
                }

                else -> { /* Handle other cases if needed */ }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        // Request recent data from the ViewModel
        viewModel.getRecent()
    }

    /**
     * Sets the recycler data based on the provided recent list.
     *
     * @param recentList The list of recent items.
     */
    private fun setRecyclerData(recentList: List<Recents.Recent>) {
        if (recentList.isNotEmpty()) {
            recentAdapter = RecentAdapter(recentList, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    // Handle item click if needed
                }
            })
            binding.rvRecent.adapter = recentAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Clean up the binding instance
        _binding = null
    }
}
