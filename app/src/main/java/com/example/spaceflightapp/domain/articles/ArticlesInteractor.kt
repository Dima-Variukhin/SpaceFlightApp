package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.data.articles.ArticleRepository
import com.example.spaceflightapp.data.articles.ArticlesDataToDomainMapper

interface ArticlesInteractor {
    suspend fun fetchArticles(): ArticlesDomain
    suspend fun update(): ArticlesDomain
    suspend fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
    )

    class Base(
        private val articleRepository: ArticleRepository,
        private val mapper: ArticlesDataToDomainMapper<ArticlesDomain>
    ) : ArticlesInteractor {
        override suspend fun fetchArticles() = articleRepository.fetchData().map(mapper)
        override suspend fun update() = articleRepository.update().map(mapper)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String,
        ) = articleRepository.changeFavorite(
            id,
            title,
            url,
            imageUrl,
            newsSite,
            summary,
            publishedAt,
            updatedAt
        )
    }
}