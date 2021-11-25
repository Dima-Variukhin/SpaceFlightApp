package com.myapp.spaceflightapp.data.reports.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ReportService {
    @GET("reports")
    suspend fun fetchReports(): ResponseBody
}