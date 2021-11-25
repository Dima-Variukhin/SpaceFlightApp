package com.myapp.spaceflightapp.presentation.apod

import com.myapp.spaceflightapp.core.Communication

interface ApodCommunication : Communication<ApodCheckUi> {
    class Base : Communication.Base<ApodCheckUi>(), ApodCommunication
}