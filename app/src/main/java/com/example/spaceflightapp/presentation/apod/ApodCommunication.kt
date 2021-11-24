package com.example.spaceflightapp.presentation.apod

import com.example.spaceflightapp.core.Communication

interface ApodCommunication : Communication<ApodCheckUi> {
    class Base : Communication.Base<ApodCheckUi>(), ApodCommunication
}