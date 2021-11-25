package com.myapp.spaceflightapp.presentation.blogs

import com.myapp.spaceflightapp.core.ErrorType
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.domain.blogs.BlogDomain
import com.myapp.spaceflightapp.domain.blogs.BlogDomainToUiMapper
import com.myapp.spaceflightapp.domain.blogs.BlogsDomainToUiMapper

class BaseBlogsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val blogMapper: BlogDomainToUiMapper<BlogUi>
) : BlogsDomainToUiMapper<BlogsUi>(resourceProvider) {
    override fun map(data: List<BlogDomain>) =
        BlogsUi.Base(ArrayList(data.map { it.map(blogMapper) }))

    override fun map(errorType: ErrorType) =
        BlogsUi.Base(ArrayList(listOf(BlogUi.Fail(errorMessage((errorType))))))
}