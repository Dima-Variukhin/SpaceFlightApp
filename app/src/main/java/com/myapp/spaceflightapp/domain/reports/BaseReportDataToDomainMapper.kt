package com.myapp.spaceflightapp.domain.reports

import com.myapp.spaceflightapp.data.reports.ReportDataToDomainMapper

class BaseReportDataToDomainMapper : ReportDataToDomainMapper<ReportDomain> {
    override fun map(
        idR: Int,
        titleR: String,
        urlR: String,
        imageUrlR: String,
        newsSiteR: String,
        summaryR: String,
        publishedAtR: String,
        updatedAtR: String
    ) = ReportDomain.Base(
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