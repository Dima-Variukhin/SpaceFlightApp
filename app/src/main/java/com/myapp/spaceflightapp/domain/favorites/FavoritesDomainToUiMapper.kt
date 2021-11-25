package com.myapp.spaceflightapp.domain.favorites

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.ResourceProvider

abstract class FavoritesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<FavoriteDomain>, T>(resourceProvider)