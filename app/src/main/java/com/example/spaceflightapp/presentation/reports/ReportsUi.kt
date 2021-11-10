package com.example.spaceflightapp.presentation.reports

import com.example.spaceflightapp.core.ListMapper

sealed class ReportsUi {
    abstract fun map(mapper: ListMapper<ReportUi>)

    data class Base(private val reports: MutableList<ReportUi>) : ReportsUi() {
        override fun map(mapper: ListMapper<ReportUi>) = mapper.map(reports)
    }
}