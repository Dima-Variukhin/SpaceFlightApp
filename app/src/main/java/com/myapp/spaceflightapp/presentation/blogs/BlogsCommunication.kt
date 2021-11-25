package com.myapp.spaceflightapp.presentation.blogs

import com.myapp.spaceflightapp.core.Communication

interface BlogsCommunication : Communication<BlogsUi> {
    class Base : Communication.Base<BlogsUi>(), BlogsCommunication
}