package com.example.spaceflightapp.domain.apod

import com.example.spaceflightapp.data.apod.ApodDataToDomainMapper


class BaseApodDataToDomainMapper : ApodDataToDomainMapper<ApodDomain> {
    override fun map(
        date: String, explanation: String, url: String, copyright: String?, title: String,
    ) = ApodDomain.Base(date, explanation, url, copyright, title)
}