package com.myapp.spaceflightapp.presentation.launches.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.adapters.LaunchDetailsAdapter
import com.myapp.spaceflightapp.presentation.launches.viewmodels.LaunchDetailsViewModel
import java.util.*

class LaunchDetailsFragment : BaseFragment(R.layout.fragment_launch_details) {
    companion object {
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_POSITION = "extra_position"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val position = arguments?.getInt(EXTRA_POSITION, 0)
        val year =
            arguments?.getString(EXTRA_YEAR, Calendar.getInstance().get(Calendar.YEAR).toString())
        recyclerView.setHasFixedSize(true)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(LaunchDetailsViewModel::class.java)
        }
        viewModel?.let { model ->
            model.launchData.observe(viewLifecycleOwner) {
                recyclerView.adapter = LaunchDetailsAdapter(it)
            }
            model.showData(year!!, position)
        }
    }
}