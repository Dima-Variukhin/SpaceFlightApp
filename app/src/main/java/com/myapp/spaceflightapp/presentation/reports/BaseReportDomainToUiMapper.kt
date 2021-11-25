package com.myapp.spaceflightapp.presentation.reports

import com.myapp.spaceflightapp.domain.reports.ReportDomainToUiMapper

class BaseReportDomainToUiMapper : ReportDomainToUiMapper<ReportUi> {
    override fun map(
        idR: Int,
        titleR: String,
        urlR: String,
        imageUrlR: String,
        newsSiteR: String,
        summaryR: String,
        publishedAtR: String,
        updatedAtR: String,
        data: String
    ) = ReportUi.Base(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR, data)
}