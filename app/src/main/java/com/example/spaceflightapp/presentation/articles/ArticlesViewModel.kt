package com.example.spaceflightapp.presentation.articles

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.domain.articles.ArticlesDomainToUiMapper
import com.example.spaceflightapp.domain.articles.ArticlesInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewModel(
    private val articlesInteractor: ArticlesInteractor,
    private val mapper: ArticlesDomainToUiMapper<ArticlesUi>,
    private val communication: ArticlesCommunication,
    private val navigator: ArticlesNavigator,
    private val navigationCommunication: NavigationCommunication,
    resourceProvider: ResourceProvider,
) : BaseViewModel(resourceProvider) {
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

    fun observe(owner: LifecycleOwner, observer: Observer<ArticlesUi>) {
        communication.observe(owner, observer)
    }

    fun init() {
        navigator.saveArticleScreen()
        fetchArticles()
    }
}