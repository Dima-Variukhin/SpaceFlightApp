package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.ResourceProvider

abstract class ArticlesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ArticleDomain>, T>(resourceProvider) {
}