package com.example.spaceflightapp.presentation.reports

import com.example.spaceflightapp.core.ErrorType
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.domain.reports.ReportDomain
import com.example.spaceflightapp.domain.reports.ReportDomainToUiMapper
import com.example.spaceflightapp.domain.reports.ReportsDomainToUiMapper


class BaseReportsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val reportMapper: ReportDomainToUiMapper<ReportUi>
) : ReportsDomainToUiMapper<ReportsUi>(resourceProvider) {
    override fun map(data: List<ReportDomain>) =
        ReportsUi.Base(ArrayList(data.map { it.map(reportMapper) }))

    override fun map(errorType: ErrorType) =
        ReportsUi.Base(ArrayList(listOf(ReportUi.Fail(errorMessage((errorType))))))
}