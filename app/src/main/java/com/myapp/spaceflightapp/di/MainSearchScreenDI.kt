package com.myapp.spaceflightapp.di

import android.app.Application
import com.google.gson.Gson
import com.myapp.spaceflightapp.data.launches.LaunchesRepository
import com.myapp.spaceflightapp.data.launches.cache.LaunchesCache
import com.myapp.spaceflightapp.data.launches.cloud.LaunchesDataMapper
import com.myapp.spaceflightapp.data.launches.cloud.LaunchesService
import com.myapp.spaceflightapp.data.launches.datasource.LaunchesDataStore
import com.myapp.spaceflightapp.data.launches.datasource.LaunchesDataStoreFactory
import com.myapp.spaceflightapp.data.upcoming.UpcomingRepository
import com.myapp.spaceflightapp.data.upcoming.cloud.UpcomingCloudToDataMapper
import com.myapp.spaceflightapp.data.upcoming.cloud.UpcomingService
import com.myapp.spaceflightapp.data.upcoming.datasource.UpcomingDataStore
import com.myapp.spaceflightapp.domain.launches.LaunchDetailsInteractor
import com.myapp.spaceflightapp.domain.launches.LaunchesInteractor
import com.myapp.spaceflightapp.domain.launches.SearchResultsInteractor
import com.myapp.spaceflightapp.domain.launches.Validator
import com.myapp.spaceflightapp.domain.upcoming.SearchUpcomingInteractor
import com.myapp.spaceflightapp.domain.upcoming.UpcomingDetailsInteractor
import com.myapp.spaceflightapp.domain.upcoming.UpcomingInteractor

object MainSearchScreenDI {
    private lateinit var launchesCache: LaunchesCache
    private var repository: LaunchesRepository? = null
    private var upcomingRepository: UpcomingRepository? = null
    private var launchDetailsInteractor: LaunchDetailsInteractor? = null
    private var upcomingDetailsInteractor: UpcomingDetailsInteractor? = null
    private var launchesInteractor: LaunchesInteractor? = null
    private var upcomingInteractor: UpcomingInteractor? = null
    private var searchUpcomingInteractor: SearchUpcomingInteractor? = null
    private var searchResultsInteractor: SearchResultsInteractor? = null

    fun initialize(app: Application) {
        launchesCache = LaunchesCache.Base(app)
    }

    fun getLaunchesInteractorImpl(): LaunchesInteractor {
        if (launchesInteractor == null)
            launchesInteractor = makeLaunchesInteractor(getLaunchesRepository())
        return launchesInteractor!!
    }

    fun getUpcomingInteractorImpl(): UpcomingInteractor {
        if (upcomingInteractor == null)
            upcomingInteractor = makeUpcomingInteractor(getUpcomingRepository())
        return upcomingInteractor!!
    }

    fun getUpcomingResultsInteractor(): SearchUpcomingInteractor {
        if (searchUpcomingInteractor == null)
            searchUpcomingInteractor = SearchUpcomingInteractor.Base(getUpcomingRepository())
        return searchUpcomingInteractor!!
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

    fun getUpcomingDetailsInteractor(): UpcomingDetailsInteractor {
        if (upcomingDetailsInteractor == null)
            upcomingDetailsInteractor = getUpcomingDetailsInteractor(getUpcomingRepository())
        return upcomingDetailsInteractor!!
    }

    private fun getLaunchDetailsInteractor(repository: LaunchesRepository) =
        LaunchDetailsInteractor.Base(repository)

    private fun getUpcomingDetailsInteractor(repository: UpcomingRepository) =
        UpcomingDetailsInteractor.Base(repository)

    private fun makeLaunchesInteractor(repository: LaunchesRepository) =
        LaunchesInteractor.Base(repository, Validator.Base())

    private fun makeUpcomingInteractor(repository: UpcomingRepository) =
        UpcomingInteractor.Base(repository)

    private fun getLaunchesRepository(): LaunchesRepository {
        if (repository == null)
            repository = LaunchesRepository.Base(
                getLaunchesDataStoreFactory(),
                LaunchesDataMapper()
            )
        return repository!!
    }

    private fun getUpcomingRepository(): UpcomingRepository {
        if (upcomingRepository == null)
            upcomingRepository = UpcomingRepository.Base(
                getUpcomingCloudDataStore(),
                UpcomingCloudToDataMapper()
            )
        return upcomingRepository!!
    }

    private fun getCacheLaunchesDataStore() =
        LaunchesDataStore.CacheLaunchesDataStore(launchesCache)

    private fun getCloudLaunchesDataStore() = LaunchesDataStore.CloudLaunchesDataStore(
        NetworkDI.getServiceSearch(LaunchesService::class.java),
        launchesCache
    )

    private fun getUpcomingCloudDataStore() = UpcomingDataStore.CloudUpcomingDataStore(
        NetworkDI.getServiceUpcoming(UpcomingService::class.java)
    )

    private fun getLaunchesDataStoreFactory() =
        LaunchesDataStoreFactory.Base(
            launchesCache,
            getCacheLaunchesDataStore(),
            getCloudLaunchesDataStore()
        )
}