package com.example.spaceflightapp.di

import android.app.Application
import com.example.spaceflightapp.data.launches.LaunchesRepository
import com.example.spaceflightapp.data.launches.cache.LaunchesCache
import com.example.spaceflightapp.data.launches.cloud.LaunchesDataMapper
import com.example.spaceflightapp.data.launches.cloud.LaunchesService
import com.example.spaceflightapp.data.launches.datasource.LaunchesDataStore
import com.example.spaceflightapp.data.launches.datasource.LaunchesDataStoreFactory
import com.example.spaceflightapp.domain.launches.LaunchDetailsInteractor
import com.example.spaceflightapp.domain.launches.LaunchesInteractor
import com.example.spaceflightapp.domain.launches.SearchResultsInteractor
import com.example.spaceflightapp.domain.launches.Validator

object MainSearchScreenDI {
    private lateinit var launchesCache: LaunchesCache
    private var repository: LaunchesRepository? = null
    private var launchDetailsInteractor: LaunchDetailsInteractor? = null
    private var launchesInteractor: LaunchesInteractor? = null
    private var searchResultsInteractor: SearchResultsInteractor? = null

    fun initialize(app: Application) {
        launchesCache = LaunchesCache.Base(app)
    }

    fun getLaunchesInteractorImpl(): LaunchesInteractor {
        if (launchesInteractor == null)
            launchesInteractor = makeLaunchesInteractor(getLaunchesRepository())
        return launchesInteractor!!
    }

    fun getSearchResultsInteractor(): SearchResultsInteractor {
        if (searchResultsInteractor == null)
            searchResultsInteractor = SearchResultsInteractor.Base(getLaunchesRepository())
        return searchResultsInteractor!!
    }

    fun getLaunchDetailsInteractor(): LaunchDetailsInteractor {
        if (launchDetailsInteractor == null)
            launchDetailsInteractor = getLaunchDetailsInteractor(getLaunchesRepository())
        return launchDetailsInteractor!!
    }

    private fun getLaunchDetailsInteractor(repository: LaunchesRepository) =
        LaunchDetailsInteractor.Base(repository)

    private fun makeLaunchesInteractor(repository: LaunchesRepository) =
        LaunchesInteractor.Base(repository, Validator.Base())

    private fun getLaunchesRepository(): LaunchesRepository {
        if (repository == null)
            repository = LaunchesRepository.Base(
                getLaunchesDataStoreFactory(),
                LaunchesDataMapper()
            )
        return repository!!
    }

    private fun getCacheLaunchesDataStore() =
        LaunchesDataStore.CacheLaunchesDataStore(launchesCache)

    private fun getCloudLaunchesDataStore() = LaunchesDataStore.CloudLaunchesDataStore(
        NetworkDI.getServiceSearch(LaunchesService::class.java),
        launchesCache
    )

    private fun getLaunchesDataStoreFactory() =
        LaunchesDataStoreFactory.Base(
            launchesCache,
            getCacheLaunchesDataStore(),
            getCloudLaunchesDataStore()
        )
}