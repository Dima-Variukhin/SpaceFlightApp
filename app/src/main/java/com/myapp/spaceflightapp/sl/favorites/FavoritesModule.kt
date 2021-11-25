package com.myapp.spaceflightapp.sl.favorites

import com.myapp.spaceflightapp.data.favorites.FavoriteRepository
import com.myapp.spaceflightapp.domain.favorites.BaseFavoriteDataToDomainMapper
import com.myapp.spaceflightapp.domain.favorites.BaseFavoritesDataToDomainMapper
import com.myapp.spaceflightapp.domain.favorites.FavoritesInteractor
import com.myapp.spaceflightapp.presentation.favorites.BaseFavoriteDomainToUiMapper
import com.myapp.spaceflightapp.presentation.favorites.BaseFavoritesDomainToUiMapper
import com.myapp.spaceflightapp.presentation.favorites.FavoritesCommunication
import com.myapp.spaceflightapp.presentation.favorites.FavoritesViewModel
import com.myapp.spaceflightapp.sl.core.BaseModule
import com.myapp.spaceflightapp.sl.core.CoreModule

class FavoritesModule(
    private val coreModule: CoreModule,
    private val repository: FavoriteRepository
) : BaseModule<FavoritesViewModel> {
    override fun viewModel(): FavoritesViewModel {
        return FavoritesViewModel(
            interactor(),
            mapper(),
            communication(),
            coreModule.navigator,
            coreModule.navigationCommunicationWeb,
            coreModule.navigationCommunicationShare,
        )
    }

    private fun interactor() = FavoritesInteractor.Base(
        repository,
        BaseFavoritesDataToDomainMapper(BaseFavoriteDataToDomainMapper())
    )

    private fun mapper() = BaseFavoritesDomainToUiMapper(
        coreModule.resourceProvider,
        BaseFavoriteDomainToUiMapper()
    )

    private fun communication() = FavoritesCommunication.Base()
}