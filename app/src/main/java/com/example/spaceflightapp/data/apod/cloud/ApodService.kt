package com.example.spaceflightapp.data.apod.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

//  https://api.nasa.gov/planetary/apod?api_key=wJS30ciGkBpEM6O7dSF2tx8zjhZaZIPrfal5wEgq
interface ApodService {
    @GET
    suspend fun fetch(@Url url: String): ResponseBody
}