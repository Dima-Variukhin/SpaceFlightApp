package com.myapp.spaceflightapp.domain.apod

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.ResourceProvider

abstract class ApodCheckDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<ApodDomain, T>(resourceProvider)