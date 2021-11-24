package com.example.spaceflightapp.sl.apod

import com.example.spaceflightapp.core.RepositoryContainer
import com.example.spaceflightapp.data.apod.ApodRepository
import com.example.spaceflightapp.data.apod.ToApodMapper
import com.example.spaceflightapp.data.apod.cloud.ApodCloudDataSource
import com.example.spaceflightapp.data.apod.cloud.ApodCloudMapper
import com.example.spaceflightapp.data.apod.cloud.ApodService
import com.example.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.example.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.example.spaceflightapp.sl.core.CoreModule

class ApodRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<ApodRepository> {
    override fun repository(): ApodRepository {
        val toApodMapper = ToApodMapper.Base()
        return ApodRepository.Base(
            apodCloudDataSource(),
            favoritesCacheDataSource(),
            ApodCloudMapper.Base(toApodMapper),
        )
    }

    private fun favoritesCacheDataSource() =
        FavoriteCacheDataSource.Base(coreModule.realmProvider, FavoriteDataToDbMapper.Base())

    private fun apodCloudDataSource() =
        ApodCloudDataSource.Base(apodService(), coreModule.gson)

    private fun apodService() = coreModule.makeService(ApodService::class.java)
}