package com.example.spaceflightapp.sl.blogs

import com.example.spaceflightapp.data.blogs.BlogRepository
import com.example.spaceflightapp.domain.blogs.BaseBlogDataToDomainMapper
import com.example.spaceflightapp.domain.blogs.BaseBlogsDataToDomainMapper
import com.example.spaceflightapp.domain.blogs.BlogsInteractor
import com.example.spaceflightapp.presentation.blogs.BaseBlogDomainToUiMapper
import com.example.spaceflightapp.presentation.blogs.BaseBlogsDomainToUiMapper
import com.example.spaceflightapp.presentation.blogs.BlogsCommunication
import com.example.spaceflightapp.presentation.blogs.BlogsViewModel
import com.example.spaceflightapp.sl.core.BaseModule
import com.example.spaceflightapp.sl.core.CoreModule

class BlogsModule(
    private val coreModule: CoreModule,
    private val repository: BlogRepository
) : BaseModule<BlogsViewModel> {
    override fun viewModel(): BlogsViewModel {
        return BlogsViewModel(
            interactor(),
            mapper(),
            communication(),
            coreModule.navigator,
            coreModule.navigationCommunicationWeb,
            coreModule.navigationCommunicationShare,
            coreModule.resourceProvider
        )
    }

    private fun interactor() = BlogsInteractor.Base(
        repository,
        BaseBlogsDataToDomainMapper(BaseBlogDataToDomainMapper())
    )

    private fun mapper() = BaseBlogsDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBlogDomainToUiMapper()
    )

    private fun communication() = BlogsCommunication.Base()
}