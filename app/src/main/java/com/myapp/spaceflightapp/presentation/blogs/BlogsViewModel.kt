package com.myapp.spaceflightapp.presentation.blogs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.core.Show
import com.myapp.spaceflightapp.domain.blogs.BlogsDomainToUiMapper
import com.myapp.spaceflightapp.domain.blogs.BlogsInteractor
import com.myapp.spaceflightapp.presentation.BaseViewModel
import com.myapp.spaceflightapp.presentation.NavigationCommunicationShare
import com.myapp.spaceflightapp.presentation.NavigationCommunicationWeb
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
        viewModelScope.debounceLaunch(300) {
            withContext(Dispatchers.IO) {
                val resultDomain = blogsInteractor.update()
                val resultUi = resultDomain.map(mapper)
                withContext(Dispatchers.Main) {
                    communication.map(resultUi)
                }
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