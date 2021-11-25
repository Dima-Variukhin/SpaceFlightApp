package com.myapp.spaceflightapp.domain.blogs

import com.myapp.spaceflightapp.core.Abstract

interface BlogDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String,
        data: String
    ): T
}