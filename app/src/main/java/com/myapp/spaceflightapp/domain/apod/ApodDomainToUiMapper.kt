package com.myapp.spaceflightapp.domain.apod

import com.myapp.spaceflightapp.core.Abstract

interface ApodDomainToUiMapper<T> : Abstract.Mapper {
    fun map(date: String, explanation: String, url: String, copyright: String?, title: String): T
}