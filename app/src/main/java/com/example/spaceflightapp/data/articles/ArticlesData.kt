package com.example.spaceflightapp.data.articles

import com.example.spaceflightapp.core.Abstract

sealed class ArticlesData : Abstract.DataObject {
    abstract fun <T> map(mapper: ArticlesDataToDomainMapper<T>): T

    data class Success(private val articles: List<ArticleData>) : ArticlesData() {
        override fun <T> map(mapper: ArticlesDataToDomainMapper<T>) = mapper.map(articles)
    }

    data class Fail(private val e: Exception) : ArticlesData() {
        override fun <T> map(mapper: ArticlesDataToDomainMapper<T>) = mapper.map(e)
    }
}