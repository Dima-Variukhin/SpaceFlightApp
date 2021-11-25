package com.myapp.spaceflightapp.domain.apod

sealed class ApodDomain {
    abstract fun <T> map(mapper: ApodDomainToUiMapper<T>): T

    data class Base(
        private val date: String,
        private val explanation: String,
        private val url: String,
        private val copyright: String?,
        private val title: String,
    ) : ApodDomain() {
        override fun <T> map(mapper: ApodDomainToUiMapper<T>) =
            mapper.map(date, explanation, url, copyright, title)
    }
}