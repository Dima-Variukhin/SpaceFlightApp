package com.myapp.spaceflightapp.data.favorites

import com.myapp.spaceflightapp.core.Abstract

abstract class FavoritesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<FavoriteData>, T>()