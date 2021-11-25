package com.myapp.spaceflightapp.domain.reports

import com.myapp.spaceflightapp.core.Abstract

interface ReportDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        idR: Int,
        titleR: String,
        urlR: String,
        imageUrlR: String,
        newsSiteR: String,
        summaryR: String,
        publishedAtR: String,
        updatedAtR: String,
        data: String
    ): T
}