package com.example.spaceflightapp.presentation.blogs

import com.example.spaceflightapp.core.Communication

interface BlogsCommunication : Communication<BlogsUi> {
    class Base : Communication.Base<BlogsUi>(), BlogsCommunication
}