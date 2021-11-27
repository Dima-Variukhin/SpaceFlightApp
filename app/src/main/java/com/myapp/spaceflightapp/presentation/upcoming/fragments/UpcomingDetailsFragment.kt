package com.myapp.spaceflightapp.presentation.upcoming.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.SearchActivity
import com.myapp.spaceflightapp.UpcomingActivity
import com.myapp.spaceflightapp.presentation.launches.adapters.LaunchDetailsAdapter
import com.myapp.spaceflightapp.presentation.launches.fragments.BaseFragment
import com.myapp.spaceflightapp.presentation.launches.viewmodels.LaunchDetailsViewModel
import com.myapp.spaceflightapp.presentation.upcoming.adapters.UpcomingDetailsAdapter
import com.myapp.spaceflightapp.presentation.upcoming.viewmodels.UpcomingDetailsViewModel
import java.util.*

class UpcomingDetailsFragment : BaseFragment(R.layout.fragment_launch_details) {
    companion object {
        const val EXTRA_POSITION = "extra_position"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val position = arguments?.getInt(EXTRA_POSITION, 0)
        recyclerView.setHasFixedSize(true)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(UpcomingDetailsViewModel::class.java)
        }
        viewModel?.let { model ->
            model.upcomingData.observe(viewLifecycleOwner) {
                recyclerView.adapter = UpcomingDetailsAdapter(it)
            }
            model.showData(position)
        }
    }
}