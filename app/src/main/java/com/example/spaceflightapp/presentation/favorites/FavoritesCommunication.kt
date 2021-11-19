package com.example.spaceflightapp.presentation.favorites

import com.example.spaceflightapp.core.Communication

interface FavoritesCommunication : Communication<FavoritesUi> {
    class Base : Communication.Base<FavoritesUi>(), FavoritesCommunication
}