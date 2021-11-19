package com.example.spaceflightapp.presentation.blogs

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


class BlogsFragment : BaseFragment<BlogsViewModel>() {
    override fun viewModelClass() = BlogsViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = BlogAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchBlogs()
        }, object : ClickListener<BlogUi> {
            override fun click(item: BlogUi) = item.open(viewModel)
        }, object : ClickListener<BlogUi> {
            override fun click(item: BlogUi) = item.share(viewModel)
        }, object : ClickListener<BlogUi> {
            override fun click(item: BlogUi) {
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
        viewModel.initBlog()
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
}