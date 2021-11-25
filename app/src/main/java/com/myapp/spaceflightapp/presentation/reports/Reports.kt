package com.myapp.spaceflightapp.presentation.reports

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.core.ClickListener
import com.myapp.spaceflightapp.core.Retry
import com.myapp.spaceflightapp.presentation.BaseFragment
import com.myapp.spaceflightapp.presentation.apod.PhotoOfTheDay
import com.google.android.material.snackbar.Snackbar


class Reports : BaseFragment<ReportsViewModel>() {
    override fun viewModelClass() = ReportsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ReportAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchReports()
        }, object : ClickListener<ReportUi> {
            override fun click(item: ReportUi) = item.open(viewModel)
        }, object : ClickListener<ReportUi> {
            override fun click(item: ReportUi) = item.share(viewModel)
        }, object : ClickListener<ReportUi> {
            override fun click(item: ReportUi) {
                Snackbar.make(
                    view,
                    R.string.change_item_status,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    item.changeFavorite(viewModel)
                    viewModel.fetchReports()
                    Toast.makeText(view.context, R.string.succesfully_Changed, Toast.LENGTH_SHORT)
                        .show()
                }.show()
            }
        }
        )
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
        if (item.itemId == R.id.apod) {
            val fragment = PhotoOfTheDay::class.java
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, bundleOf())
                .commit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun update() = viewModel.update()
}