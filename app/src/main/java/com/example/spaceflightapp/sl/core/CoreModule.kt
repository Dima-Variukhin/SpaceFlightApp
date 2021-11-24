package com.example.spaceflightapp.sl.core

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.spaceflightapp.BuildConfig
import com.example.spaceflightapp.MainViewModel
import com.example.spaceflightapp.core.RealmProvider
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.presentation.*
import com.example.spaceflightapp.presentation.articles.ArticlesViewModel
import com.example.spaceflightapp.presentation.blogs.BlogsViewModel
import com.example.spaceflightapp.presentation.favorites.FavoritesViewModel
import com.example.spaceflightapp.presentation.reports.ReportsViewModel
import com.google.gson.Gson
import io.realm.Realm
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
        Realm.init(context)
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
        realmProvider = RealmProvider.Base()
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