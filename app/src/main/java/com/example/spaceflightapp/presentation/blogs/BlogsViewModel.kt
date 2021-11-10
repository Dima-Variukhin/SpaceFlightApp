package com.example.spaceflightapp.presentation.blogs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.core.Show
import com.example.spaceflightapp.domain.blogs.BlogsDomainToUiMapper
import com.example.spaceflightapp.domain.blogs.BlogsInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunication
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

    fun init() {
        navigator.saveBlogScreen()
        fetchBlogs()
    }

    override fun open(data: String) {
        navigationCommunicationWeb.map(data)
    }
}