package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.core.ListMapper

sealed class ArticlesUi {
    abstract fun map(mapper: ListMapper<ArticleUi>)

    data class Base(private val articles: MutableList<ArticleUi>) : ArticlesUi() {
        override fun map(mapper: ListMapper<ArticleUi>) = mapper.map(articles)
    }
}