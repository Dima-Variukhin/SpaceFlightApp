package com.example.spaceflightapp.domain.favorites

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.ResourceProvider

abstract class FavoritesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<FavoriteDomain>, T>(resourceProvider)