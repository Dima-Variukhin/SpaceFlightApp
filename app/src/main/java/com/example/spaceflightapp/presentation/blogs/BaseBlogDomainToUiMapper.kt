package com.example.spaceflightapp.presentation.blogs

import com.example.spaceflightapp.domain.blogs.BlogDomainToUiMapper

class BaseBlogDomainToUiMapper : BlogDomainToUiMapper<BlogUi> {
    override fun map(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String,
        data : String
    ) = BlogUi.Base(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB, data)
}