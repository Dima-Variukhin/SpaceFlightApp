package com.myapp.spaceflightapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.spaceflightapp.MainViewModel
import com.myapp.spaceflightapp.presentation.apod.ApodViewModel
import com.myapp.spaceflightapp.presentation.articles.ArticlesViewModel
import com.myapp.spaceflightapp.presentation.blogs.BlogsViewModel
import com.myapp.spaceflightapp.presentation.favorites.FavoritesViewModel
import com.myapp.spaceflightapp.presentation.reports.ReportsViewModel
import java.lang.IllegalStateException

class ViewModelsFactory(private val dependencyContainer: DependencyContainer) :
    ViewModelProvider.Factory {
    private val map = HashMap<Class<*>, Feature>(6).apply {
        put(MainViewModel::class.java, Feature.MAIN)
        put(ArticlesViewModel::class.java, Feature.ARTICLES)
        put(BlogsViewModel::class.java, Feature.BLOGS)
        put(ReportsViewModel::class.java, Feature.REPORTS)
        put(FavoritesViewModel::class.java, Feature.FAVORITES)
        put(ApodViewModel::class.java, Feature.APOD)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}