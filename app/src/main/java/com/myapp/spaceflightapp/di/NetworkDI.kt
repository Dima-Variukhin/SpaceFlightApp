package com.myapp.spaceflightapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkDI {
    private const val BASE_URL_SEARCH = "https://api.spacexdata.com/v2/"
    private const val BASE_URL_UPCOMING = "https://api.spacexdata.com/v5/"
    private lateinit var retrofitSearch: Retrofit
    private lateinit var retrofitUpcoming: Retrofit

    fun initializeSearch() {
        retrofitSearch = getRetrofitSearch(getOkHttpClient(getInterceptor()))
    }

    fun initializeUpcoming() {
        retrofitUpcoming = getRetrofitUpcoming(getOkHttpClient(getInterceptor()))
    }

    fun <T> getServiceSearch(className: Class<T>): T = retrofitSearch.create(className)

    fun <T> getServiceUpcoming(className: Class<T>): T = retrofitUpcoming.create(className)


    private fun getRetrofitSearch(client: OkHttpClient) =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_SEARCH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    private fun getRetrofitUpcoming(client: OkHttpClient) =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_UPCOMING)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    private fun getOkHttpClient(interceptor: Interceptor) =
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

    private fun getInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}