package com.myapp.spaceflightapp.sl.blogs

import com.myapp.spaceflightapp.data.blogs.BlogRepository
import com.myapp.spaceflightapp.domain.blogs.BaseBlogDataToDomainMapper
import com.myapp.spaceflightapp.domain.blogs.BaseBlogsDataToDomainMapper
import com.myapp.spaceflightapp.domain.blogs.BlogsInteractor
import com.myapp.spaceflightapp.presentation.blogs.BaseBlogDomainToUiMapper
import com.myapp.spaceflightapp.presentation.blogs.BaseBlogsDomainToUiMapper
import com.myapp.spaceflightapp.presentation.blogs.BlogsCommunication
import com.myapp.spaceflightapp.presentation.blogs.BlogsViewModel
import com.myapp.spaceflightapp.sl.core.BaseModule
import com.myapp.spaceflightapp.sl.core.CoreModule

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