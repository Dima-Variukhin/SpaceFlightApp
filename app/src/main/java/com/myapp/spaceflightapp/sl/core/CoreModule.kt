package com.myapp.spaceflightapp.sl.core

import android.content.Context
import com.google.gson.Gson
import com.myapp.spaceflightapp.MainViewModel
import com.myapp.spaceflightapp.core.RealmProvider
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.presentation.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class CoreModule : BaseModule<MainViewModel> {
    private companion object {
        const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"
    }

    lateinit var resourceProvider: ResourceProvider
    lateinit var gson: Gson
    private lateinit var retrofit: Retrofit
    lateinit var realmProvider: RealmProvider
    lateinit var navigator: Navigator
    private lateinit var navigationCommunication: NavigationCommunication
    lateinit var navigationCommunicationWeb: NavigationCommunicationWeb
    lateinit var navigationCommunicationShare: NavigationCommunicationShare
    private lateinit var mainNavigator: MainNavigator
    private lateinit var screenPosition: ScreenPosition

    fun init(context: Context) {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        gson = Gson()
        resourceProvider = ResourceProvider.Base(context)
        realmProvider = RealmProvider.Base(context)
        mainNavigator = Navigator.Base(resourceProvider)
        navigator = Navigator.Base(resourceProvider)
        navigationCommunication = NavigationCommunication.Base()
        navigationCommunicationWeb = NavigationCommunicationWeb.Base()
        navigationCommunicationShare = NavigationCommunicationShare.Base()
        screenPosition = ScreenPosition.Base(navigator)
    }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)
    override fun viewModel() =
        MainViewModel(
            screenPosition,
            mainNavigator,
            navigationCommunication,
            navigationCommunicationWeb,
            navigationCommunicationShare
        )
}