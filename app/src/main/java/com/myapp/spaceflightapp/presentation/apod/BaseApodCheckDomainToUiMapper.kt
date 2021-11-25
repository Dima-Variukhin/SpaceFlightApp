package com.myapp.spaceflightapp.presentation.apod

import com.myapp.spaceflightapp.core.ErrorType
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.domain.apod.ApodCheckDomainToUiMapper
import com.myapp.spaceflightapp.domain.apod.ApodDomain
import com.myapp.spaceflightapp.domain.apod.ApodDomainToUiMapper

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