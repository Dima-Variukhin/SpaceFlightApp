package com.myapp.spaceflightapp.domain.articles

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.ResourceProvider

abstract class ArticlesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ArticleDomain>, T>(resourceProvider)