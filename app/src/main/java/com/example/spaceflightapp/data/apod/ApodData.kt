package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract

interface ApodData : Abstract.DataObject {
    fun <T> map(mapper: ApodDataToDomainMapper<T>): T

    class Base(
        private val date: String,
        private val explanation: String,
        private val url: String,
        private val copyright: String?,
        private val title: String,
    ) : ApodData {
        override fun <T> map(mapper: ApodDataToDomainMapper<T>) =
            mapper.map(date, explanation, url, copyright, title)
    }
}