package com.example.spaceflightapp.data.articles

import com.example.spaceflightapp.core.BaseRepository
import com.example.spaceflightapp.data.articles.cache.ArticleCacheDataSource
import com.example.spaceflightapp.data.articles.cache.ArticleCacheMapper
import com.example.spaceflightapp.data.articles.cache.ArticleDb
import com.example.spaceflightapp.data.articles.cloud.ArticleCloud
import com.example.spaceflightapp.data.articles.cloud.ArticleCloudDataSource
import com.example.spaceflightapp.data.articles.cloud.ArticleCloudMapper

interface ArticleRepository : BaseRepository<ArticlesData> {
    class Base(
        private val cloudDataSource: ArticleCloudDataSource,
        private val cacheDataSource: ArticleCacheDataSource,
        articleCloudMapper: ArticleCloudMapper,
        articleCacheMapper: ArticleCacheMapper,
    ) : BaseRepository.Base<ArticleDb, ArticleCloud, ArticleData, ArticlesData>(
        cacheDataSource, articleCloudMapper, articleCacheMapper
    ), ArticleRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchArticles()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<ArticleData>) = ArticlesData.Success(list)
        override fun returnFail(e: Exception) = ArticlesData.Fail(e)
    }
}