package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.apod.cloud.ApodCloudDataSource
import com.example.spaceflightapp.data.apod.cloud.ApodCloudMapper
import com.example.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource

interface ApodRepository : Abstract.DataObject {
    suspend fun fetch(url: String): ApodCheckData
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
        private val apodCloudDataSource: ApodCloudDataSource,
        private val favoriteCacheDataSource: FavoriteCacheDataSource,
        private val apodCloudMapper: ApodCloudMapper
    ) : ApodRepository {
        override suspend fun fetch(url: String) = try {
            val cloud = apodCloudDataSource.fetch(url)
            val data = apodCloudMapper.map(cloud)
            ApodCheckData.Success(data)
        } catch (e: Exception) {
            ApodCheckData.Fail(e)
        }
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
