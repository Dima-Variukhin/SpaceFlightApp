package com.example.spaceflightapp.data.launches.datasource

import com.example.spaceflightapp.data.launches.cache.LaunchesCache

interface LaunchesDataStoreFactory {
    enum class Priority {
        CLOUD,
        CACHE
    }

    fun create(year: String, priority: Priority): LaunchesDataStore

    class Base(
        private val launchesCache: LaunchesCache,
        private val cacheLaunchesDataStore: LaunchesDataStore.CacheLaunchesDataStore,
        private val cloudLaunchesDataStore: LaunchesDataStore.CloudLaunchesDataStore
    ) : LaunchesDataStoreFactory {
        override fun create(year: String, priority: Priority) =
            if (priority == Priority.CLOUD || !launchesCache.isCached(year))
                cloudLaunchesDataStore
            else cacheLaunchesDataStore
    }
}
