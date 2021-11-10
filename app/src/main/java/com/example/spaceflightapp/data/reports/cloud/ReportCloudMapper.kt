package com.example.spaceflightapp.data.reports.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.reports.ReportData
import com.example.spaceflightapp.data.reports.ToReportMapper

interface ReportCloudMapper : Abstract.Mapper.Data<List<ReportCloud>, List<ReportData>> {
    class Base(private val reportMapper: ToReportMapper<ReportData>) : ReportCloudMapper {
        override fun map(data: List<ReportCloud>) =
            data.map { reportData ->
                reportData.map(reportMapper)
            }
    }
}