package com.myapp.spaceflightapp.sl.core

import com.myapp.spaceflightapp.sl.apod.ApodModule
import com.myapp.spaceflightapp.sl.apod.ApodRepositoryContainer
import com.myapp.spaceflightapp.sl.articles.ArticlesModule
import com.myapp.spaceflightapp.sl.articles.ArticlesRepositoryContainer
import com.myapp.spaceflightapp.sl.blogs.BlogsModule
import com.myapp.spaceflightapp.sl.blogs.BlogsRepositoryContainer
import com.myapp.spaceflightapp.sl.favorites.FavoritesModule
import com.myapp.spaceflightapp.sl.favorites.FavoritesRepositoryContainer
import com.myapp.spaceflightapp.sl.reports.ReportsModule
import com.myapp.spaceflightapp.sl.reports.ReportsRepositoryContainer

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