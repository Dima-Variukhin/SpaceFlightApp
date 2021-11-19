package com.example.spaceflightapp.domain.favorites

import com.example.spaceflightapp.data.favorites.FavoriteRepository
import com.example.spaceflightapp.data.favorites.FavoritesDataToDomainMapper

interface FavoritesInteractor {
    suspend fun fetchFavorites(): FavoritesDomain
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
        private val favoriteRepository: FavoriteRepository,
        private val mapper: FavoritesDataToDomainMapper<FavoritesDomain>
    ) : FavoritesInteractor {
        override suspend fun fetchFavorites() = favoriteRepository.fetchData().map(mapper)
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
            favoriteRepository.changeFavorite(
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