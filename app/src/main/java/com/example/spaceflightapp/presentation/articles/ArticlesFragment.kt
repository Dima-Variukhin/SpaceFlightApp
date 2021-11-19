package com.example.spaceflightapp.presentation.articles

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.ClickListener
import com.example.spaceflightapp.core.Retry
import com.example.spaceflightapp.presentation.BaseFragment
import com.google.android.material.snackbar.Snackbar

class ArticlesFragment : BaseFragment<ArticlesViewModel>() {
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
        return super.onOptionsItemSelected(item)
    }

    private fun update() = viewModel.update()

    private companion object {
        const val NOT = 0
        const val ALREADY = 1
    }
}
