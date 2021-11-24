package com.example.spaceflightapp.domain.apod

import com.example.spaceflightapp.core.Abstract

interface ApodDomainToUiMapper<T> : Abstract.Mapper {
    fun map(date: String, explanation: String, url: String, copyright: String?, title: String): T
}