package com.myapp.spaceflightapp.presentation.apod

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.core.Show
import com.myapp.spaceflightapp.domain.apod.ApodCheckDomainToUiMapper
import com.myapp.spaceflightapp.domain.apod.ApodInteractor
import com.myapp.spaceflightapp.presentation.BaseViewModel
import com.myapp.spaceflightapp.presentation.NavigationCommunicationShare
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