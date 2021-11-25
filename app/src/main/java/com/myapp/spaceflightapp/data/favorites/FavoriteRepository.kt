package com.myapp.spaceflightapp.data.favorites


import com.myapp.spaceflightapp.core.BaseRepository
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheMapper
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDb
import com.myapp.spaceflightapp.data.favorites.cloud.FavoriteCloud
import com.myapp.spaceflightapp.data.favorites.cloud.FavoriteCloudDataSource
import com.myapp.spaceflightapp.data.favorites.cloud.FavoriteCloudMapper

interface FavoriteRepository : BaseRepository<FavoritesData> {
    class Base(
        private val favoriteCloudDataSource: FavoriteCloudDataSource,
        private val favoriteCacheDataSource: FavoriteCacheDataSource,
        favoriteCacheMapper: FavoriteCacheMapper,
        favoriteCloudMapper: FavoriteCloudMapper,
    ) : BaseRepository.Base<FavoriteDb, FavoriteCloud, FavoriteData, FavoritesData>(
        favoriteCacheDataSource,
        favoriteCloudMapper,
        favoriteCacheMapper
    ), FavoriteRepository {
        override suspend fun fetchCloudData() = favoriteCloudDataSource.fetchFavorites()
        override fun getCachedDataList() = favoriteCacheDataSource.read()
        override fun returnSuccess(list: List<FavoriteData>) = FavoritesData.Success(list)
        override fun returnFail(e: Exception) = FavoritesData.Fail(e)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) {
            favoriteCacheDataSource.changeFavorite(
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
}

