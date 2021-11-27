package com.myapp.spaceflightapp.data.apod.cloud

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApodService {
    @GET("https://api.nasa.gov/planetary/apod?api_key=wJS30ciGkBpEM6O7dSF2tx8zjhZaZIPrfal5wEgq")
   suspend fun fetch(): ResponseBody
}