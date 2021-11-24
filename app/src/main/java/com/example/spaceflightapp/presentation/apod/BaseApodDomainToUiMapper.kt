package com.example.spaceflightapp.presentation.apod

import com.example.spaceflightapp.domain.apod.ApodDomainToUiMapper

class BaseApodDomainToUiMapper : ApodDomainToUiMapper<ApodUi> {
    override fun map(
        date: String, explanation: String, url: String, copyright: String?, title: String,
    ) = ApodUi.Base(date, explanation, url, copyright, title)
}