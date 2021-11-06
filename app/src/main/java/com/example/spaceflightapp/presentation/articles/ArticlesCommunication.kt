package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.core.Communication

interface ArticlesCommunication : Communication<ArticlesUi> {
    class Base : Communication.Base<ArticlesUi>(), ArticlesCommunication
}