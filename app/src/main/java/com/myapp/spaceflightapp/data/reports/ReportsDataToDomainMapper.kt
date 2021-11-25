package com.myapp.spaceflightapp.data.reports

import com.myapp.spaceflightapp.core.Abstract

abstract class ReportsDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<ReportData>, T>()