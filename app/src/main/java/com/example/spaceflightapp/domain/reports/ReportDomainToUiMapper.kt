package com.example.spaceflightapp.domain.reports

import com.example.spaceflightapp.core.Abstract

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