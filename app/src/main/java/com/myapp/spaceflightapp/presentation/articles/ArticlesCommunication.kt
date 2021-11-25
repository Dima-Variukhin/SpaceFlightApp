package com.myapp.spaceflightapp.presentation.articles

import com.myapp.spaceflightapp.core.Communication

interface ArticlesCommunication : Communication<ArticlesUi> {
    class Base : Communication.Base<ArticlesUi>(), ArticlesCommunication
}

