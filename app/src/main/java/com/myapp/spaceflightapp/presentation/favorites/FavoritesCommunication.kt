package com.myapp.spaceflightapp.presentation.favorites

import com.myapp.spaceflightapp.core.Communication

interface FavoritesCommunication : Communication<FavoritesUi> {
    class Base : Communication.Base<FavoritesUi>(), FavoritesCommunication
}