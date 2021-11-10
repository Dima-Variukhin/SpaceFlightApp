package com.example.spaceflightapp.domain.reports

import com.example.spaceflightapp.core.ErrorType



sealed class ReportsDomain {
    abstract fun <T> map(mapper: ReportsDomainToUiMapper<T>): T

    data class Success(private val blogs: List<ReportDomain>) : ReportsDomain() {
        override fun <T> map(mapper: ReportsDomainToUiMapper<T>) = mapper.map(blogs)
    }

    data class Fail(private val errorType: ErrorType) : ReportsDomain() {
        override fun <T> map(mapper: ReportsDomainToUiMapper<T>) = mapper.map(errorType)
    }
}