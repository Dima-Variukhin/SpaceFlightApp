package com.myapp.spaceflightapp.data.favorites.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.favorites.FavoriteData

interface FavoriteCloudMapper : Abstract.Mapper.Data<List<FavoriteCloud>, List<FavoriteData>> {
    class Base : FavoriteCloudMapper {
        override fun map(data: List<FavoriteCloud>): List<FavoriteData> {
            return emptyList()
        }
    }
}