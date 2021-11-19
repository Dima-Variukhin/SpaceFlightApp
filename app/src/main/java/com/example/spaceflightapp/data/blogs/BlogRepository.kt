package com.example.spaceflightapp.data.blogs

import com.example.spaceflightapp.core.BaseRepository
import com.example.spaceflightapp.data.blogs.cache.BlogCacheMapper
import com.example.spaceflightapp.data.blogs.cache.BlogDb
import com.example.spaceflightapp.data.blogs.cache.BlogsCacheDataSource
import com.example.spaceflightapp.data.blogs.cloud.BlogCloud
import com.example.spaceflightapp.data.blogs.cloud.BlogCloudMapper
import com.example.spaceflightapp.data.blogs.cloud.BlogsCloudDataSource
import com.example.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource


interface BlogRepository : BaseRepository<BlogsData> {
    class Base(
        private val cloudDataSource: BlogsCloudDataSource,
        private val cacheDataSource: BlogsCacheDataSource,
        private val favoriteCacheDataSource: FavoriteCacheDataSource,
        blogCloudMapper: BlogCloudMapper,
        blogCacheMapper: BlogCacheMapper,
    ) : BaseRepository.Base<BlogDb, BlogCloud, BlogData, BlogsData>(
        cacheDataSource, blogCloudMapper, blogCacheMapper
    ), BlogRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchBlogs()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<BlogData>) = BlogsData.Success(list)
        override fun returnFail(e: Exception) = BlogsData.Fail(e)
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