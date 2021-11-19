package com.example.spaceflightapp.presentation.blogs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.core.Show
import com.example.spaceflightapp.domain.blogs.BlogsDomainToUiMapper
import com.example.spaceflightapp.domain.blogs.BlogsInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunicationShare
import com.example.spaceflightapp.presentation.NavigationCommunicationWeb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlogsViewModel(
    private val blogsInteractor: BlogsInteractor,
    private val mapper: BlogsDomainToUiMapper<BlogsUi>,
    private val communication: BlogsCommunication,
    private val navigator: BlogsNavigator,
    private val navigationCommunicationWeb: NavigationCommunicationWeb,
    private val navigationCommunicationShare: NavigationCommunicationShare,
    resourceProvider: ResourceProvider,
) : BaseViewModel(), Show {
    fun fetchBlogs() {
        communication.map(BlogsUi.Base(ArrayList(listOf(BlogUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = blogsInteractor.fetchBlogs()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun update() {
        communication.map(BlogsUi.Base(ArrayList(listOf(BlogUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = blogsInteractor.update()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BlogsUi>) {
        communication.observe(owner, observer)
    }

    fun initBlog() {
        navigator.saveBlogScreen()
        fetchBlogs()
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
            blogsInteractor.changeFavorite(
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