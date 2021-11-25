package com.myapp.spaceflightapp.data.articles

import com.myapp.spaceflightapp.core.BaseRepository
import com.myapp.spaceflightapp.data.articles.cache.ArticleCacheDataSource
import com.myapp.spaceflightapp.data.articles.cache.ArticleCacheMapper
import com.myapp.spaceflightapp.data.articles.cache.ArticleDb
import com.myapp.spaceflightapp.data.articles.cloud.ArticleCloud
import com.myapp.spaceflightapp.data.articles.cloud.ArticleCloudDataSource
import com.myapp.spaceflightapp.data.articles.cloud.ArticleCloudMapper
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource

interface ArticleRepository : BaseRepository<ArticlesData> {
    class Base(
        private val cloudDataSource: ArticleCloudDataSource,
        private val cacheDataSource: ArticleCacheDataSource,
        private val favoriteCacheDataSource: FavoriteCacheDataSource,
        articleCloudMapper: ArticleCloudMapper,
        articleCacheMapper: ArticleCacheMapper,
    ) : BaseRepository.Base<ArticleDb, ArticleCloud, ArticleData, ArticlesData>(
        cacheDataSource, articleCloudMapper, articleCacheMapper
    ), ArticleRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchArticles()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<ArticleData>) = ArticlesData.Success(list)
        override fun returnFail(e: Exception) = ArticlesData.Fail(e)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = favoriteCacheDataSource.changeFavorite(
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
