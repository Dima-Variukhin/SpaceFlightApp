package com.example.spaceflightapp.presentation.articles

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.core.Show
import com.example.spaceflightapp.domain.articles.ArticlesDomainToUiMapper
import com.example.spaceflightapp.domain.articles.ArticlesInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunicationShare
import com.example.spaceflightapp.presentation.NavigationCommunicationWeb
import kotlinx.coroutines.*

import kotlin.collections.ArrayList

class ArticlesViewModel(
    private val articlesInteractor: ArticlesInteractor,
    private val mapper: ArticlesDomainToUiMapper<ArticlesUi>,
    private val communication: ArticlesCommunication,
    private val navigator: ArticlesNavigator,
    private val navigationCommunicationWeb: NavigationCommunicationWeb,
    private val navigationCommunicationShare: NavigationCommunicationShare,
    resourceProvider: ResourceProvider,
) : BaseViewModel(), Show {
    fun fetchArticles() {
        communication.map(ArticlesUi.Base(ArrayList(listOf(ArticleUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = articlesInteractor.fetchArticles()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun update() {
        communication.map(ArticlesUi.Base(ArrayList(listOf(ArticleUi.Progress))))
        viewModelScope.debounceLaunch(300) {
            withContext(Dispatchers.IO) {
                val resultDomain = articlesInteractor.update()
                val resultUi = resultDomain.map(mapper)
                withContext(Dispatchers.Main) {
                    communication.map(resultUi)
                }
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<ArticlesUi>) {
        communication.observe(owner, observer)
    }

    fun initArticles() {
        navigator.saveArticleScreen()
        fetchArticles()
    }

    override fun open(data: String) {
        navigationCommunicationWeb.map(data)
    }

    override fun share(data: String) {
        navigationCommunicationShare.map(data)
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
            articlesInteractor.changeFavorite(
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

