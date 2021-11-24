package com.example.spaceflightapp.domain.apod

import com.example.spaceflightapp.data.apod.ApodCheckDataToDomainMapper
import com.example.spaceflightapp.data.apod.ApodData
import com.example.spaceflightapp.data.apod.ApodDataToDomainMapper
import java.lang.Exception

class BaseApodCheckDataToDomainMapper(private val apodMapper: ApodDataToDomainMapper<ApodDomain>) :
    ApodCheckDataToDomainMapper<ApodCheckDomain>() {
    override fun map(data: ApodData): ApodCheckDomain {
        return ApodCheckDomain.Success(data.map(apodMapper))
    }

    override fun map(e: Exception) = ApodCheckDomain.Fail(errorType(e))
}