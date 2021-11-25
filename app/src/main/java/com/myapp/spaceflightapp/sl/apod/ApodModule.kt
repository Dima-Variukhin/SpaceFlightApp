package com.myapp.spaceflightapp.sl.apod

import com.myapp.spaceflightapp.data.apod.ApodRepository
import com.myapp.spaceflightapp.domain.apod.ApodInteractor
import com.myapp.spaceflightapp.domain.apod.BaseApodCheckDataToDomainMapper
import com.myapp.spaceflightapp.domain.apod.BaseApodDataToDomainMapper
import com.myapp.spaceflightapp.presentation.apod.ApodCommunication
import com.myapp.spaceflightapp.presentation.apod.ApodViewModel
import com.myapp.spaceflightapp.presentation.apod.BaseApodCheckDomainToUiMapper
import com.myapp.spaceflightapp.presentation.apod.BaseApodDomainToUiMapper
import com.myapp.spaceflightapp.sl.core.BaseModule
import com.myapp.spaceflightapp.sl.core.CoreModule

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