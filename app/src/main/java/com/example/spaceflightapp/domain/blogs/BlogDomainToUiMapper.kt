package com.example.spaceflightapp.domain.blogs

import com.example.spaceflightapp.core.Abstract

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