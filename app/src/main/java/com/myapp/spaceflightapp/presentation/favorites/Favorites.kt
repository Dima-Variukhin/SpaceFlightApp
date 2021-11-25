package com.myapp.spaceflightapp.presentation.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.core.ClickListener
import com.myapp.spaceflightapp.core.Retry
import com.myapp.spaceflightapp.presentation.BaseFragment
import com.google.android.material.snackbar.Snackbar


class Favorites : BaseFragment<FavoritesViewModel>() {
    override fun viewModelClass() = FavoritesViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = FavoriteAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchFavorites()
        }, object : ClickListener<FavoriteUi> {
            override fun click(item: FavoriteUi) = item.open(viewModel)
        }, object : ClickListener<FavoriteUi> {
            override fun click(item: FavoriteUi) = item.share(viewModel)
        }, object : ClickListener<FavoriteUi> {
            override fun click(item: FavoriteUi) {
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
        setAdapter(adapter)
        viewModel.observe(this) {
            it.map(adapter)
        }
        viewModel.initFavorites()
    }
}