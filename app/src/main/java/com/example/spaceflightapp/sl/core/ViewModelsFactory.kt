package com.example.spaceflightapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceflightapp.MainViewModel
import com.example.spaceflightapp.presentation.articles.ArticlesViewModel
import com.example.spaceflightapp.presentation.blogs.BlogsViewModel
import java.lang.IllegalStateException

class ViewModelsFactory(private val dependencyContainer: DependencyContainer) :
    ViewModelProvider.Factory {
    private val map = HashMap<Class<*>, Feature>(3).apply {
        put(MainViewModel::class.java, Feature.MAIN)
        put(ArticlesViewModel::class.java, Feature.ARTICLES)
        put(BlogsViewModel::class.java, Feature.BLOGS)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}