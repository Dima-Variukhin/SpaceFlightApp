package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract

interface ApodDataToDomainMapper<T> : Abstract.Mapper {
    fun map(date: String, explanation: String, url: String, copyright: String?, title: String): T
}