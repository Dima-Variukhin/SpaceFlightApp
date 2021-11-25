package com.myapp.spaceflightapp.presentation.articles

import com.myapp.spaceflightapp.core.ListMapper

sealed class ArticlesUi {
    abstract fun map(mapper: ListMapper<ArticleUi>)

    data class Base(private val articles: MutableList<ArticleUi>) : ArticlesUi() {
        override fun map(mapper: ListMapper<ArticleUi>) = mapper.map(articles)
    }
}