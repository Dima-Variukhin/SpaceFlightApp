package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}