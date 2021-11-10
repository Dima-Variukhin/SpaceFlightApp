package com.example.spaceflightapp.sl.core

import com.example.spaceflightapp.sl.articles.ArticlesModule
import com.example.spaceflightapp.sl.articles.ArticlesRepositoryContainer
import com.example.spaceflightapp.sl.blogs.BlogsModule
import com.example.spaceflightapp.sl.blogs.BlogsRepositoryContainer

interface DependencyContainer {
    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule) : DependencyContainer {
        override fun module(feature: Feature) = when (feature) {
            Feature.MAIN -> coreModule
            Feature.ARTICLES -> ArticlesModule(coreModule, articlesRepository())
            Feature.BLOGS -> BlogsModule(coreModule, blogsRepository())
        }

        private fun articlesRepository() = ArticlesRepositoryContainer(coreModule).repository()
        private fun blogsRepository() = BlogsRepositoryContainer(coreModule).repository()
    }
}