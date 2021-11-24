package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract


sealed class ApodCheckData : Abstract.DataObject {
    abstract fun <T> map(mapper: ApodCheckDataToDomainMapper<T>): T

    data class Success(private val apod: ApodData) : ApodCheckData() {
        override fun <T> map(mapper: ApodCheckDataToDomainMapper<T>) = mapper.map(apod)
    }

    data class Fail(private val e: Exception) : ApodCheckData() {
        override fun <T> map(mapper: ApodCheckDataToDomainMapper<T>) = mapper.map(e)
    }
}