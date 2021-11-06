package com.example.spaceflightapp.sl.core

import android.content.Context
import com.example.spaceflightapp.MainViewModel
import com.example.spaceflightapp.core.RealmProvider
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.presentation.NavigationCommunication
import com.example.spaceflightapp.presentation.Navigator
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
    lateinit var retrofit: Retrofit
    lateinit var realmProvider: RealmProvider
    lateinit var navigator: Navigator
    lateinit var navigationCommunication: NavigationCommunication

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
        navigator = Navigator.Base(resourceProvider)
        navigationCommunication = NavigationCommunication.Base()

    }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)
    override fun viewModel() = MainViewModel(navigator, navigationCommunication)
}