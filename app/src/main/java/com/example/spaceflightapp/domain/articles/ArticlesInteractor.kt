package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.data.articles.ArticleRepository
import com.example.spaceflightapp.data.articles.ArticlesDataToDomainMapper

interface ArticlesInteractor {
    suspend fun fetchArticles(): ArticlesDomain
    suspend fun update(): ArticlesDomain

    class Base(
        private val articleRepository: ArticleRepository,
        private val mapper: ArticlesDataToDomainMapper<ArticlesDomain>
    ) : ArticlesInteractor {
        override suspend fun fetchArticles() = articleRepository.fetchData().map(mapper)
        override suspend fun update() = articleRepository.update().map(mapper)
    }
}