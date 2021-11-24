package com.example.spaceflightapp.presentation.apod

import com.example.spaceflightapp.core.ErrorType
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.domain.apod.ApodCheckDomainToUiMapper
import com.example.spaceflightapp.domain.apod.ApodDomain
import com.example.spaceflightapp.domain.apod.ApodDomainToUiMapper

class BaseApodCheckDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val apodMapper: ApodDomainToUiMapper<ApodUi>
) : ApodCheckDomainToUiMapper<ApodCheckUi>(resourceProvider) {
    override fun map(data: ApodDomain): ApodCheckUi {
        return ApodCheckUi.Base(mutableListOf(data.map(apodMapper)))
    }

    override fun map(errorType: ErrorType): ApodCheckUi {
        return ApodCheckUi.Base(mutableListOf(ApodUi.Fail(errorMessage(errorType))))
    }
}