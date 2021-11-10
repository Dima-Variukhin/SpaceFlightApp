package com.example.spaceflightapp.domain.reports

import com.example.spaceflightapp.data.reports.ReportRepository
import com.example.spaceflightapp.data.reports.ReportsDataToDomainMapper

interface ReportsInteractor {
    suspend fun fetchReports(): ReportsDomain
    suspend fun update(): ReportsDomain

    class Base(
        private val reportRepository: ReportRepository,
        private val mapper: ReportsDataToDomainMapper<ReportsDomain>
    ) : ReportsInteractor {
        override suspend fun fetchReports() = reportRepository.fetchData().map(mapper)
        override suspend fun update() = reportRepository.update().map(mapper)
    }
}