package com.myapp.spaceflightapp.sl.articles

import com.myapp.spaceflightapp.data.articles.ArticleRepository
import com.myapp.spaceflightapp.domain.articles.ArticlesInteractor
import com.myapp.spaceflightapp.domain.articles.BaseArticleDataToDomainMapper
import com.myapp.spaceflightapp.domain.articles.BaseArticlesDataToDomainMapper
import com.myapp.spaceflightapp.presentation.articles.ArticlesCommunication
import com.myapp.spaceflightapp.presentation.articles.ArticlesViewModel
import com.myapp.spaceflightapp.presentation.articles.BaseArticleDomainToUiMapper
import com.myapp.spaceflightapp.presentation.articles.BaseArticlesDomainToUiMapper
import com.myapp.spaceflightapp.sl.core.BaseModule
import com.myapp.spaceflightapp.sl.core.CoreModule

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
            coreModule.navigationCommunicationWeb,
            coreModule.navigationCommunicationShare,
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