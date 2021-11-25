package com.myapp.spaceflightapp.data.reports.cloud


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


interface ReportCloudDataSource {
    suspend fun fetchReports(): List<ReportCloud>

    class Base(private val service: ReportService, private val gson: Gson) :
        ReportCloudDataSource {
        override suspend fun fetchReports(): List<ReportCloud> = gson.fromJson(
            service.fetchReports().string(),
            object : TypeToken<List<ReportCloud.Base>>() {}.type
        )
    }
}