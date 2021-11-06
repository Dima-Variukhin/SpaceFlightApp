package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.core.ErrorType

sealed class ArticlesDomain {
    abstract fun <T> map(mapper: ArticlesDomainToUiMapper<T>): T

    data class Success(private val articles: List<ArticleDomain>) : ArticlesDomain() {
        override fun <T> map(mapper: ArticlesDomainToUiMapper<T>) = mapper.map(articles)
    }

    data class Fail(private val errorType: ErrorType) : ArticlesDomain() {
        override fun <T> map(mapper: ArticlesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}