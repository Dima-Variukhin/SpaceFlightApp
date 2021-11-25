package com.myapp.spaceflightapp.domain.reports

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.ResourceProvider

abstract class ReportsDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ReportDomain>, T>(resourceProvider)