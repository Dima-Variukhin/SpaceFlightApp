package com.myapp.spaceflightapp.data.reports.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.reports.ReportData
import com.myapp.spaceflightapp.data.reports.ToReportMapper

interface ReportCloudMapper : Abstract.Mapper.Data<List<ReportCloud>, List<ReportData>> {
    class Base(private val reportMapper: ToReportMapper<ReportData>) : ReportCloudMapper {
        override fun map(data: List<ReportCloud>) =
            data.map { reportData ->
                reportData.map(reportMapper)
            }
    }
}