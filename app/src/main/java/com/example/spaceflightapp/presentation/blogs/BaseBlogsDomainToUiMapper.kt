package com.example.spaceflightapp.presentation.blogs

import com.example.spaceflightapp.core.ErrorType
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.domain.blogs.BlogDomain
import com.example.spaceflightapp.domain.blogs.BlogDomainToUiMapper
import com.example.spaceflightapp.domain.blogs.BlogsDomainToUiMapper

class BaseBlogsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val blogMapper: BlogDomainToUiMapper<BlogUi>
) : BlogsDomainToUiMapper<BlogsUi>(resourceProvider) {
    override fun map(data: List<BlogDomain>) =
        BlogsUi.Base(ArrayList(data.map { it.map(blogMapper) }))

    override fun map(errorType: ErrorType) =
        BlogsUi.Base(ArrayList(listOf(BlogUi.Fail(errorMessage((errorType))))))
}