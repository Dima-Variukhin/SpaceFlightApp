package com.example.spaceflightapp.data.reports

import com.example.spaceflightapp.core.BaseRepository
import com.example.spaceflightapp.data.reports.cache.ReportCacheDataSource
import com.example.spaceflightapp.data.reports.cache.ReportCacheMapper
import com.example.spaceflightapp.data.reports.cache.ReportDb
import com.example.spaceflightapp.data.reports.cloud.ReportCloud
import com.example.spaceflightapp.data.reports.cloud.ReportCloudDataSource
import com.example.spaceflightapp.data.reports.cloud.ReportCloudMapper

interface ReportRepository : BaseRepository<ReportsData> {
    class Base(
        private val cloudDataSource: ReportCloudDataSource,
        private val cacheDataSource: ReportCacheDataSource,
        reportCloudMapper: ReportCloudMapper,
        reportCacheMapper: ReportCacheMapper,
    ) : BaseRepository.Base<ReportDb, ReportCloud, ReportData, ReportsData>(
        cacheDataSource, reportCloudMapper, reportCacheMapper
    ), ReportRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchReports()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<ReportData>) = ReportsData.Success(list)
        override fun returnFail(e: Exception) = ReportsData.Fail(e)
    }
}