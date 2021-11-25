package com.myapp.spaceflightapp.domain.blogs

import com.myapp.spaceflightapp.data.blogs.BlogDataToDomainMapper

class BaseBlogDataToDomainMapper : BlogDataToDomainMapper<BlogDomain> {
    override fun map(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String,

    ) = BlogDomain.Base(
        idB,
        titleB,
        urlB,
        imageUrlB,
        newsSiteB,
        summaryB,
        publishedAtB,
        updatedAtB,
    )
}