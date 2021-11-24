package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract

interface ToApodMapper<T> : Abstract.Mapper {
    fun map(date: String, explanation: String, url: String, copyright: String?, title: String): T

    class Base :
        ToApodMapper<ApodData> {
        override fun map(
            date: String, explanation: String, url: String, copyright: String?, title: String
        ) = ApodData.Base(date, explanation, url, copyright, title)
    }
}