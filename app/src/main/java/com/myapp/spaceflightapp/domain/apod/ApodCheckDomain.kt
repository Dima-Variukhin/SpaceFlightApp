package com.myapp.spaceflightapp.domain.apod

import com.myapp.spaceflightapp.core.ErrorType

sealed class ApodCheckDomain {
    abstract fun <T> map(mapper: ApodCheckDomainToUiMapper<T>): T

    data class Success(private val apod: ApodDomain) : ApodCheckDomain() {
        override fun <T> map(mapper: ApodCheckDomainToUiMapper<T>) = mapper.map(apod)
    }

    data class Fail(private val errorType: ErrorType) : ApodCheckDomain() {
        override fun <T> map(mapper: ApodCheckDomainToUiMapper<T>) = mapper.map(errorType)
    }
}