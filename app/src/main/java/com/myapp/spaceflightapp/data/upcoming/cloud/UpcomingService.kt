package com.myapp.spaceflightapp.data.upcoming.cloud

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface UpcomingService {
    @GET("launches/upcoming")
    fun getLaunchesAsync(): Deferred<Response<List<UpcomingCloud>>>
}