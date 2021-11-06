package com.example.spaceflightapp.sl.articles

import com.example.spaceflightapp.data.articles.ArticleRepository
import com.example.spaceflightapp.domain.articles.ArticlesInteractor
import com.example.spaceflightapp.domain.articles.BaseArticleDataToDomainMapper
import com.example.spaceflightapp.domain.articles.BaseArticlesDataToDomainMapper
import com.example.spaceflightapp.presentation.articles.ArticlesCommunication
import com.example.spaceflightapp.presentation.articles.ArticlesViewModel
import com.example.spaceflightapp.presentation.articles.BaseArticleDomainToUiMapper
import com.example.spaceflightapp.presentation.articles.BaseArticlesDomainToUiMapper
import com.example.spaceflightapp.sl.core.BaseModule
import com.example.spaceflightapp.sl.core.CoreModule

class ArticlesModule(
    private val coreModule: CoreModule,
    private val repository: ArticleRepository
) : BaseModule<ArticlesViewModel> {
    override fun viewModel(): ArticlesViewModel {
        return ArticlesViewModel(
            interactor(),
            mapper(),
            communication(),
            coreModule.navigator,
            coreModule.navigationCommunication,
            coreModule.resourceProvider
        )
    }

    private fun interactor() = ArticlesInteractor.Base(
        repository,
        BaseArticlesDataToDomainMapper(BaseArticleDataToDomainMapper())
    )

    private fun mapper() = BaseArticlesDomainToUiMapper(
        coreModule.resourceProvider,
        BaseArticleDomainToUiMapper()
    )

    private fun communication() = ArticlesCommunication.Base()
}