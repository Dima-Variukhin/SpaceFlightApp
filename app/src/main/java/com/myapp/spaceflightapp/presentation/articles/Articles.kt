package com.myapp.spaceflightapp.presentation.articles

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


class Articles : BaseFragment<ArticlesViewModel>() {
    override fun viewModelClass() = ArticlesViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ArticleAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchArticles()
        }, object : ClickListener<ArticleUi> {
            override fun click(item: ArticleUi) = item.open(viewModel)
        }, object : ClickListener<ArticleUi> {
            override fun click(item: ArticleUi) = item.share(viewModel)
        }, object : ClickListener<ArticleUi> {
            override fun click(item: ArticleUi) {
                Snackbar.make(
                    view,
                    R.string.change_item_status,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    item.changeFavorite(viewModel)
                    viewModel.fetchArticles()
                    Toast.makeText(view.context, R.string.succesfully_Changed, Toast.LENGTH_SHORT)
                        .show()
                }.show()
            }
        })

        setAdapter(adapter)
        viewModel.observe(this) {
            it.map(adapter)
        }
        viewModel.initArticles()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
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

    private companion object
}
