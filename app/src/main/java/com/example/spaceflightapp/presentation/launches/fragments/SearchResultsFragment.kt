package com.example.spaceflightapp.presentation.launches.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.viewmodels.SearchResultsViewModel
import java.util.*

class SearchResultsFragment : BaseFragment(R.layout.fragment_search_results) {

    companion object {
        const val EXTRA_YEAR = "extra_year"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchResultsListView: ListView = view.findViewById(R.id.searchResultsListView)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(SearchResultsViewModel::class.java)
        }

        viewModel?.let { model ->
            model.results.observe(viewLifecycleOwner) {
                searchResultsListView.apply {
                    adapter =
                        ArrayAdapter(requireContext(), android.R.layout.simple_selectable_list_item, it)
                    onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        Navigation.findNavController(view)
                            .navigate(R.id.details_screen, Bundle().apply {
                                putInt(LaunchDetailsFragment.EXTRA_POSITION, position)
                                putString(
                                    LaunchDetailsFragment.EXTRA_YEAR, arguments?.getString(
                                        EXTRA_YEAR
                                    )
                                )
                            })
                    }
                }
            }
            model.showResults(
                arguments?.getString(EXTRA_YEAR) ?: Calendar.getInstance().get(Calendar.YEAR)
                    .toString()
            )
        }
    }
}