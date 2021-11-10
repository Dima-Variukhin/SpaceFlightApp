package com.example.spaceflightapp.data.reports

import com.example.spaceflightapp.core.Abstract

interface ReportDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        idR: Int,
        titleR: String,
        urlR: String,
        imageUrlR: String,
        newsSiteR: String,
        summaryR: String,
        publishedAtR: String,
        updatedAtR: String
    ): T
}