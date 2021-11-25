package com.myapp.spaceflightapp.data.reports

import com.myapp.spaceflightapp.core.Abstract

interface ToReportMapper<T> : Abstract.Mapper {
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

    class Base : ToReportMapper<ReportData> {
        override fun map(
            idR: Int,
            titleR: String,
            urlR: String,
            imageUrlR: String,
            newsSiteR: String,
            summaryR: String,
            publishedAtR: String,
            updatedAtR: String
        ) = ReportData.Base(
            idR,
            titleR,
            urlR,
            imageUrlR,
            newsSiteR,
            summaryR,
            publishedAtR,
            updatedAtR
        )
    }
}