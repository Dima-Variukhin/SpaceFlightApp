package com.example.spaceflightapp.sl.favorites

import com.example.spaceflightapp.core.RepositoryContainer
import com.example.spaceflightapp.data.favorites.FavoriteRepository
import com.example.spaceflightapp.data.favorites.ToFavoriteMapper
import com.example.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.example.spaceflightapp.data.favorites.cache.FavoriteCacheMapper
import com.example.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.example.spaceflightapp.data.favorites.cloud.FavoriteCloudDataSource
import com.example.spaceflightapp.data.favorites.cloud.FavoriteCloudMapper

import com.example.spaceflightapp.sl.core.CoreModule

class FavoritesRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<FavoriteRepository.Base> {
    override fun repository(): FavoriteRepository.Base {
        val toFavoriteMapper = ToFavoriteMapper.Base()
        return FavoriteRepository.Base(
            favoritesCloudDataSource(),
            favoritesCacheDataSource(),
            FavoriteCacheMapper.Base(toFavoriteMapper),
            FavoriteCloudMapper.Base(),
        )
    }

    private fun favoritesCacheDataSource() =
        FavoriteCacheDataSource.Base(coreModule.realmProvider, FavoriteDataToDbMapper.Base())

    private fun favoritesCloudDataSource() = FavoriteCloudDataSource.Base()
}