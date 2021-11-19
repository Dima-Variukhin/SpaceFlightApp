package com.example.spaceflightapp.data.favorites

import com.example.spaceflightapp.core.Abstract

abstract class FavoritesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<FavoriteData>, T>()