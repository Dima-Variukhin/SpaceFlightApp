package com.example.spaceflightapp.domain.reports

import com.example.spaceflightapp.data.reports.ReportData
import com.example.spaceflightapp.data.reports.ReportDataToDomainMapper
import com.example.spaceflightapp.data.reports.ReportsDataToDomainMapper
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