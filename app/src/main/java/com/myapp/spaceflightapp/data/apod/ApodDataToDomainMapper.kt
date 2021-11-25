package com.myapp.spaceflightapp.data.apod

import com.myapp.spaceflightapp.core.Abstract

interface ApodDataToDomainMapper<T> : Abstract.Mapper {
    fun map(date: String, explanation: String, url: String, copyright: String?, title: String): T
}