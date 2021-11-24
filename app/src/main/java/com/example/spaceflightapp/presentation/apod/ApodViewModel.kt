package com.example.spaceflightapp.presentation.apod

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.core.Show
import com.example.spaceflightapp.domain.apod.ApodCheckDomainToUiMapper
import com.example.spaceflightapp.domain.apod.ApodInteractor
import com.example.spaceflightapp.domain.articles.ArticlesDomainToUiMapper
import com.example.spaceflightapp.domain.articles.ArticlesInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunicationShare
import com.example.spaceflightapp.presentation.NavigationCommunicationWeb
import com.example.spaceflightapp.presentation.articles.ArticleUi
import com.example.spaceflightapp.presentation.articles.ArticlesCommunication
import com.example.spaceflightapp.presentation.articles.ArticlesNavigator
import com.example.spaceflightapp.presentation.articles.ArticlesUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApodViewModel(
    private val apodInteractor: ApodInteractor,
    private val mapper: ApodCheckDomainToUiMapper<ApodCheckUi>,
    private val communication: ApodCommunication,
    private val navigator: ApodNavigator,
    private val navigationCommunicationShare: NavigationCommunicationShare,
    resourceProvider: ResourceProvider,
) : BaseViewModel(), Show {
    fun fetch(url: String) {
        communication.map(ApodCheckUi.Base(ArrayList(listOf(ApodUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = apodInteractor.fetch(url)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<ApodCheckUi>) {
        communication.observe(owner, observer)
    }

    fun initApod(url: String) {
        navigator.saveApodScreen()
        fetch(url)
    }
    override fun share(data: String) {
        navigationCommunicationShare.map(data)
    }

    override fun open(data: String) {

    }
    override fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            apodInteractor.changeFavorite(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt,
            )
        }
    }
}