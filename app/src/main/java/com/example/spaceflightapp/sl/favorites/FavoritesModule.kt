package com.example.spaceflightapp.sl.favorites

import com.example.spaceflightapp.data.favorites.FavoriteRepository
import com.example.spaceflightapp.domain.favorites.BaseFavoriteDataToDomainMapper
import com.example.spaceflightapp.domain.favorites.BaseFavoritesDataToDomainMapper
import com.example.spaceflightapp.domain.favorites.FavoritesInteractor
import com.example.spaceflightapp.presentation.favorites.BaseFavoriteDomainToUiMapper
import com.example.spaceflightapp.presentation.favorites.BaseFavoritesDomainToUiMapper
import com.example.spaceflightapp.presentation.favorites.FavoritesCommunication
import com.example.spaceflightapp.presentation.favorites.FavoritesViewModel
import com.example.spaceflightapp.sl.core.BaseModule
import com.example.spaceflightapp.sl.core.CoreModule

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
            coreModule.resourceProvider
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