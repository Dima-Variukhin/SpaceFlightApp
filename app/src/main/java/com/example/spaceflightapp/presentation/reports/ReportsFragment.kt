package com.example.spaceflightapp.presentation.reports

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.ClickListener
import com.example.spaceflightapp.core.Retry
import com.example.spaceflightapp.presentation.BaseFragment


class ReportsFragment : BaseFragment<ReportsViewModel>() {
    override fun viewModelClass() = ReportsViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ReportAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchReports()
        }, object : ClickListener<ReportUi> {
            override fun click(item: ReportUi) = item.open(viewModel)
        })
        setAdapter(adapter)
        viewModel.observe(this) {
            it.map(adapter)
        }
        viewModel.initReports()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.data) {
            update()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun update() = viewModel.update()
}