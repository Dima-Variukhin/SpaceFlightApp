package com.example.spaceflightapp.data.reports

import com.example.spaceflightapp.core.Abstract

abstract class ReportsDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<ReportData>, T>() {
}