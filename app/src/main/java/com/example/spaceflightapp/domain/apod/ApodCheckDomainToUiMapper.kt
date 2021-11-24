package com.example.spaceflightapp.domain.apod

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.ResourceProvider

abstract class ApodCheckDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<ApodDomain, T>(resourceProvider)