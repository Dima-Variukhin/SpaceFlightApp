package com.myapp.spaceflightapp.domain.blogs

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.ResourceProvider

abstract class BlogsDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BlogDomain>, T>(resourceProvider)