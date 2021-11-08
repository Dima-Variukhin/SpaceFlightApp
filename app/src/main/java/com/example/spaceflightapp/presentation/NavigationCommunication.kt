package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}

interface NavigationCommunicationWeb : Communication<String> {
    class Base : Communication.Base<String>(), NavigationCommunicationWeb
}

