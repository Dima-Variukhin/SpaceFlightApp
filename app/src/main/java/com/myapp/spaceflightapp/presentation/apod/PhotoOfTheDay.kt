package com.myapp.spaceflightapp.presentation.apod

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.core.ClickListener
import com.myapp.spaceflightapp.core.Retry
import com.myapp.spaceflightapp.presentation.BaseFragment
import com.google.android.material.snackbar.Snackbar

class PhotoOfTheDay : BaseFragment<ApodViewModel>() {
    override fun viewModelClass() = ApodViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val adapter = ApodAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetch(URL)
        }, object : ClickListener<ApodUi> {
            override fun click(item: ApodUi) = item.share(viewModel)
        }, object : ClickListener<ApodUi> {
            override fun click(item: ApodUi) {
                Snackbar.make(
                    view,
                    R.string.change_item_status,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    item.changeFavorite(viewModel)
                    Toast.makeText(view.context, R.string.succesfully_Changed, Toast.LENGTH_SHORT)
                        .show()
                }.show()
            }
        })
        recyclerView.setHasFixedSize(true)
        setAdapter(adapter)
        viewModel.observe(this) {
            it.map(adapter)
        }
        viewModel.initApod(URL)
    }

    companion object {
        private const val URL =
            "https://api.nasa.gov/planetary/apod?api_key=wJS30ciGkBpEM6O7dSF2tx8zjhZaZIPrfal5wEgq"
    }
}