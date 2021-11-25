package com.myapp.spaceflightapp.data.reports

import com.myapp.spaceflightapp.core.Abstract

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