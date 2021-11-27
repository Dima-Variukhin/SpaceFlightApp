package com.myapp.spaceflightapp.presentation.upcoming.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.fragments.BaseFragment
import com.myapp.spaceflightapp.presentation.launches.fragments.LaunchDetailsFragment
import com.myapp.spaceflightapp.presentation.upcoming.viewmodels.UpcomingSearchResultsViewModel

class UpcomingSearchResultsFragment : BaseFragment(R.layout.fragment_search_results) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchResultsListView: ListView = view.findViewById(R.id.searchResultsListView)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(UpcomingSearchResultsViewModel::class.java)
        }

        viewModel?.let { model ->
            model.results.observe(viewLifecycleOwner) {
                searchResultsListView.apply {
                    adapter =
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_selectable_list_item,
                            it
                        )
                    onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        Navigation.findNavController(view)
                            .navigate(R.id.details_screen, Bundle().apply {
                                putInt(LaunchDetailsFragment.EXTRA_POSITION, position)

                            })
                    }
                }
            }
            model.showResults()
        }
    }
}