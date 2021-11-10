package com.example.spaceflightapp.sl.reports

import com.example.spaceflightapp.core.RepositoryContainer
import com.example.spaceflightapp.data.reports.ReportRepository
import com.example.spaceflightapp.data.reports.ToReportMapper
import com.example.spaceflightapp.data.reports.cache.ReportCacheDataSource
import com.example.spaceflightapp.data.reports.cache.ReportCacheMapper
import com.example.spaceflightapp.data.reports.cache.ReportDataToDbMapper
import com.example.spaceflightapp.data.reports.cloud.ReportCloudDataSource
import com.example.spaceflightapp.data.reports.cloud.ReportCloudMapper
import com.example.spaceflightapp.data.reports.cloud.ReportService
import com.example.spaceflightapp.sl.core.CoreModule

class ReportsRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<ReportRepository> {
    override fun repository(): ReportRepository {
        val toReportMapper = ToReportMapper.Base()
        return ReportRepository.Base(
            reportsCloudDataSource(),
            reportsCacheDataSource(),
            ReportCloudMapper.Base(toReportMapper),
            ReportCacheMapper.Base(toReportMapper)
        )
    }

    private fun reportsCacheDataSource() =
        ReportCacheDataSource.Base(coreModule.realmProvider, ReportDataToDbMapper.Base())

    private fun reportsCloudDataSource() =
        ReportCloudDataSource.Base(reportsService(), coreModule.gson)

    private fun reportsService() = coreModule.makeService(ReportService::class.java)
}