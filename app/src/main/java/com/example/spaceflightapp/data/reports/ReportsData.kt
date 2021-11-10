package com.example.spaceflightapp.data.reports

import com.example.spaceflightapp.core.Abstract

sealed class ReportsData : Abstract.DataObject {
    abstract fun <T> map(mapper: ReportsDataToDomainMapper<T>): T

    data class Success(private val reports: List<ReportData>) : ReportsData() {
        override fun <T> map(mapper: ReportsDataToDomainMapper<T>) = mapper.map(reports)
    }

    data class Fail(private val e: Exception) : ReportsData() {
        override fun <T> map(mapper: ReportsDataToDomainMapper<T>) = mapper.map(e)
    }
}