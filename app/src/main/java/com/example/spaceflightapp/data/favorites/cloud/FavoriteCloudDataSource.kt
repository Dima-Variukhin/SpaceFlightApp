package com.example.spaceflightapp.data.favorites.cloud

interface FavoriteCloudDataSource {
    suspend fun fetchFavorites(): List<FavoriteCloud>
    class Base() : FavoriteCloudDataSource {
        override suspend fun fetchFavorites(): List<FavoriteCloud> {
            return emptyList()
        }
    }
}