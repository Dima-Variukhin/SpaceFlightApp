package com.example.spaceflightapp.domain.reports

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.ResourceProvider

abstract class ReportsDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ReportDomain>, T>(resourceProvider)