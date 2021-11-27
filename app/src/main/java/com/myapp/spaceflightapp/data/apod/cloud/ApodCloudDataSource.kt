package com.myapp.spaceflightapp.data.apod.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


interface ApodCloudDataSource {
    suspend fun fetch(): ApodCloud

    class Base(
        private val service: ApodService,
        private val gson: Gson
    ) : ApodCloudDataSource {
        override suspend fun fetch(): ApodCloud = gson.fromJson(
            service.fetch().string(),
            object : TypeToken<ApodCloud.Base>() {}.type
        )
    }
}