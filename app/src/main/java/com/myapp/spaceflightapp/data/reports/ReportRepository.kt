package com.myapp.spaceflightapp.data.reports

import com.myapp.spaceflightapp.core.BaseRepository
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.reports.cache.ReportCacheDataSource
import com.myapp.spaceflightapp.data.reports.cache.ReportCacheMapper
import com.myapp.spaceflightapp.data.reports.cache.ReportDb
import com.myapp.spaceflightapp.data.reports.cloud.ReportCloud
import com.myapp.spaceflightapp.data.reports.cloud.ReportCloudDataSource
import com.myapp.spaceflightapp.data.reports.cloud.ReportCloudMapper

interface ReportRepository : BaseRepository<ReportsData> {
    class Base(
        private val cloudDataSource: ReportCloudDataSource,
        private val cacheDataSource: ReportCacheDataSource,
        private val favoriteCacheDataSource: FavoriteCacheDataSource,
        reportCloudMapper: ReportCloudMapper,
        reportCacheMapper: ReportCacheMapper,
    ) : BaseRepository.Base<ReportDb, ReportCloud, ReportData, ReportsData>(
        cacheDataSource, reportCloudMapper, reportCacheMapper
    ), ReportRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchReports()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<ReportData>) = ReportsData.Success(list)
        override fun returnFail(e: Exception) = ReportsData.Fail(e)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = favoriteCacheDataSource.changeFavorite(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt
            )
        }
    }
