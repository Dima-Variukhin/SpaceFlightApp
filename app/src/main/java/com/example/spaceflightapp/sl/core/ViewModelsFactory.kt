package com.example.spaceflightapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceflightapp.MainViewModel
import com.example.spaceflightapp.presentation.articles.ArticlesViewModel
import java.lang.IllegalStateException

class ViewModelsFactory(private val dependencyContainer: DependencyContainer) :
    ViewModelProvider.Factory {
    private val map = HashMap<Class<*>, Feature>(2).apply {
        put(MainViewModel::class.java, Feature.MAIN)
        put(ArticlesViewModel::class.java, Feature.ARTICLES)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}