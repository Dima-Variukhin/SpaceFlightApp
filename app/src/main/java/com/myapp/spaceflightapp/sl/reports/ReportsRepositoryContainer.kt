package com.myapp.spaceflightapp.sl.reports

import com.myapp.spaceflightapp.core.RepositoryContainer
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.myapp.spaceflightapp.data.reports.ReportRepository
import com.myapp.spaceflightapp.data.reports.ToReportMapper
import com.myapp.spaceflightapp.data.reports.cache.ReportCacheDataSource
import com.myapp.spaceflightapp.data.reports.cache.ReportCacheMapper
import com.myapp.spaceflightapp.data.reports.cache.ReportDataToDbMapper
import com.myapp.spaceflightapp.data.reports.cloud.ReportCloudDataSource
import com.myapp.spaceflightapp.data.reports.cloud.ReportCloudMapper
import com.myapp.spaceflightapp.data.reports.cloud.ReportService
import com.myapp.spaceflightapp.sl.core.CoreModule

class ReportsRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<ReportRepository> {
    override fun repository(): ReportRepository {
        val toReportMapper = ToReportMapper.Base()
        return ReportRepository.Base(
            reportsCloudDataSource(),
            reportsCacheDataSource(),
            favoritesCacheDataSource(),
            ReportCloudMapper.Base(toReportMapper),
            ReportCacheMapper.Base(toReportMapper)
        )
    }

    private fun reportsCacheDataSource() =
        ReportCacheDataSource.Base(coreModule.realmProvider, ReportDataToDbMapper.Base())

    private fun favoritesCacheDataSource() =
        FavoriteCacheDataSource.Base(coreModule.realmProvider, FavoriteDataToDbMapper.Base())

    private fun reportsCloudDataSource() =
        ReportCloudDataSource.Base(reportsService(), coreModule.gson)

    private fun reportsService() = coreModule.makeService(ReportService::class.java)
}