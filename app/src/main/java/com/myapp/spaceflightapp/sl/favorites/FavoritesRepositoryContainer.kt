package com.myapp.spaceflightapp.sl.favorites

import com.myapp.spaceflightapp.core.RepositoryContainer
import com.myapp.spaceflightapp.data.favorites.FavoriteRepository
import com.myapp.spaceflightapp.data.favorites.ToFavoriteMapper
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheMapper
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.myapp.spaceflightapp.data.favorites.cloud.FavoriteCloudDataSource
import com.myapp.spaceflightapp.data.favorites.cloud.FavoriteCloudMapper
import com.myapp.spaceflightapp.sl.core.CoreModule

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