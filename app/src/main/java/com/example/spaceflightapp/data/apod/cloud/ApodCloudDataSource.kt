package com.example.spaceflightapp.data.apod.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


interface ApodCloudDataSource {
    suspend fun fetch(url: String): ApodCloud

    class Base(
        private val service: ApodService,
        private val gson: Gson
    ) : ApodCloudDataSource {
        override suspend fun fetch(url: String): ApodCloud = gson.fromJson(
            service.fetch(url).string(),
            object : TypeToken<ApodCloud.Base>() {}.type
        )
    }
}