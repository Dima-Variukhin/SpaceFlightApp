package com.myapp.spaceflightapp.presentation.reports

import com.myapp.spaceflightapp.core.ErrorType
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.domain.reports.ReportDomain
import com.myapp.spaceflightapp.domain.reports.ReportDomainToUiMapper
import com.myapp.spaceflightapp.domain.reports.ReportsDomainToUiMapper


class BaseReportsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val reportMapper: ReportDomainToUiMapper<ReportUi>
) : ReportsDomainToUiMapper<ReportsUi>(resourceProvider) {
    override fun map(data: List<ReportDomain>) =
        ReportsUi.Base(ArrayList(data.map { it.map(reportMapper) }))

    override fun map(errorType: ErrorType) =
        ReportsUi.Base(ArrayList(listOf(ReportUi.Fail(errorMessage((errorType))))))
}