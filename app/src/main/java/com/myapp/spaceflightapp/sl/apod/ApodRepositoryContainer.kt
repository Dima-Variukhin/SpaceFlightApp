package com.myapp.spaceflightapp.sl.apod

import com.myapp.spaceflightapp.core.RepositoryContainer
import com.myapp.spaceflightapp.data.apod.ApodRepository
import com.myapp.spaceflightapp.data.apod.ToApodMapper
import com.myapp.spaceflightapp.data.apod.cloud.ApodCloudDataSource
import com.myapp.spaceflightapp.data.apod.cloud.ApodCloudMapper
import com.myapp.spaceflightapp.data.apod.cloud.ApodService
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.myapp.spaceflightapp.sl.core.CoreModule

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