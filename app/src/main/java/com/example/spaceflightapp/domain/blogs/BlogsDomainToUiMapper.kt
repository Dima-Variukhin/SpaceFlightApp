package com.example.spaceflightapp.domain.blogs

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.ResourceProvider

abstract class BlogsDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BlogDomain>, T>(resourceProvider)