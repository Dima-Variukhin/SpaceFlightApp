package com.myapp.spaceflightapp.data.blogs

import com.myapp.spaceflightapp.core.Abstract

interface BlogDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String
    ): T
}