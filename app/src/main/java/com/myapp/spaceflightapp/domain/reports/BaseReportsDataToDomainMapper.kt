package com.myapp.spaceflightapp.domain.reports

import com.myapp.spaceflightapp.data.reports.ReportData
import com.myapp.spaceflightapp.data.reports.ReportDataToDomainMapper
import com.myapp.spaceflightapp.data.reports.ReportsDataToDomainMapper
import java.lang.Exception

class BaseReportsDataToDomainMapper(private val reportMapper: ReportDataToDomainMapper<ReportDomain>) :
    ReportsDataToDomainMapper<ReportsDomain>() {
    override fun map(data: List<ReportData>): ReportsDomain {
        val domainList = mutableListOf<ReportDomain>()
        data.forEach { reportData ->
            domainList.add(reportData.map(reportMapper))
        }
        return ReportsDomain.Success(domainList)
    }

    override fun map(e: Exception) = ReportsDomain.Fail(errorType(e))
}