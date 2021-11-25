package com.myapp.spaceflightapp.presentation.apod

import com.myapp.spaceflightapp.domain.apod.ApodDomainToUiMapper

class BaseApodDomainToUiMapper : ApodDomainToUiMapper<ApodUi> {
    override fun map(
        date: String, explanation: String, url: String, copyright: String?, title: String,
    ) = ApodUi.Base(date, explanation, url, copyright, title)
}