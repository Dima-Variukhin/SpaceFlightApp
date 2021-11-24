package com.example.spaceflightapp.sl.core

import com.example.spaceflightapp.sl.apod.ApodModule
import com.example.spaceflightapp.sl.apod.ApodRepositoryContainer
import com.example.spaceflightapp.sl.articles.ArticlesModule
import com.example.spaceflightapp.sl.articles.ArticlesRepositoryContainer
import com.example.spaceflightapp.sl.blogs.BlogsModule
import com.example.spaceflightapp.sl.blogs.BlogsRepositoryContainer
import com.example.spaceflightapp.sl.favorites.FavoritesModule
import com.example.spaceflightapp.sl.favorites.FavoritesRepositoryContainer
import com.example.spaceflightapp.sl.reports.ReportsModule
import com.example.spaceflightapp.sl.reports.ReportsRepositoryContainer

interface DependencyContainer {
    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule) : DependencyContainer {
        override fun module(feature: Feature) = when (feature) {
            Feature.MAIN -> coreModule
            Feature.ARTICLES -> ArticlesModule(coreModule, articlesRepository())
            Feature.BLOGS -> BlogsModule(coreModule, blogsRepository())
            Feature.REPORTS -> ReportsModule(coreModule, reportsRepository())
            Feature.FAVORITES -> FavoritesModule(coreModule, favoritesRepository())
            Feature.APOD -> ApodModule(coreModule, apodRepository())
        }

        private fun articlesRepository() = ArticlesRepositoryContainer(coreModule).repository()
        private fun blogsRepository() = BlogsRepositoryContainer(coreModule).repository()
        private fun reportsRepository() = ReportsRepositoryContainer(coreModule).repository()
        private fun favoritesRepository() = FavoritesRepositoryContainer(coreModule).repository()
        private fun apodRepository() = ApodRepositoryContainer(coreModule).repository()
    }
}