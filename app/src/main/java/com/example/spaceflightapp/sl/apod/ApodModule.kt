package com.example.spaceflightapp.sl.apod

import com.example.spaceflightapp.data.apod.ApodRepository
import com.example.spaceflightapp.domain.apod.ApodInteractor
import com.example.spaceflightapp.domain.apod.BaseApodCheckDataToDomainMapper
import com.example.spaceflightapp.domain.apod.BaseApodDataToDomainMapper
import com.example.spaceflightapp.presentation.apod.ApodCommunication
import com.example.spaceflightapp.presentation.apod.ApodViewModel
import com.example.spaceflightapp.presentation.apod.BaseApodCheckDomainToUiMapper
import com.example.spaceflightapp.presentation.apod.BaseApodDomainToUiMapper
import com.example.spaceflightapp.sl.core.BaseModule
import com.example.spaceflightapp.sl.core.CoreModule

class ApodModule(
    private val coreModule: CoreModule,
    private val repository: ApodRepository
) : BaseModule<ApodViewModel> {
    override fun viewModel(): ApodViewModel {
        return ApodViewModel(
            interactor(),
            mapper(),
            communication(),
            coreModule.navigator,
            coreModule.navigationCommunicationShare,
            coreModule.resourceProvider
        )
    }

    private fun interactor() = ApodInteractor.Base(
        repository,
        BaseApodCheckDataToDomainMapper(BaseApodDataToDomainMapper())
    )

    private fun mapper() = BaseApodCheckDomainToUiMapper(
        coreModule.resourceProvider,
        BaseApodDomainToUiMapper()
    )

    private fun communication() = ApodCommunication.Base()
}