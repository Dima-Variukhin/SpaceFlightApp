package com.example.spaceflightapp.data.reports.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.reports.ReportData
import com.example.spaceflightapp.data.reports.ToReportMapper

interface ReportCacheMapper : Abstract.Mapper.Data<List<ReportDb>, List<ReportData>> {
    class Base(private val mapper: ToReportMapper<ReportData>) : ReportCacheMapper {
        override fun map(data: List<ReportDb>) = data.map { reportDb -> reportDb.map(mapper) }
    }
}