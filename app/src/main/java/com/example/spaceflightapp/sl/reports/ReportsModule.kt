package com.example.spaceflightapp.sl.reports


import com.example.spaceflightapp.data.reports.ReportRepository
import com.example.spaceflightapp.domain.reports.BaseReportDataToDomainMapper
import com.example.spaceflightapp.domain.reports.BaseReportsDataToDomainMapper
import com.example.spaceflightapp.domain.reports.ReportsInteractor
import com.example.spaceflightapp.presentation.reports.BaseReportDomainToUiMapper
import com.example.spaceflightapp.presentation.reports.BaseReportsDomainToUiMapper
import com.example.spaceflightapp.presentation.reports.ReportsCommunication
import com.example.spaceflightapp.presentation.reports.ReportsViewModel
import com.example.spaceflightapp.sl.core.BaseModule
import com.example.spaceflightapp.sl.core.CoreModule

class ReportsModule(
    private val coreModule: CoreModule,
    private val repository: ReportRepository
) : BaseModule<ReportsViewModel> {
    override fun viewModel(): ReportsViewModel {
        return ReportsViewModel(
            interactor(),
            mapper(),
            communication(),
            coreModule.navigator,
            coreModule.navigationCommunicationWeb,
            coreModule.resourceProvider
        )
    }

    private fun interactor() = ReportsInteractor.Base(
        repository,
        BaseReportsDataToDomainMapper(BaseReportDataToDomainMapper())
    )

    private fun mapper() = BaseReportsDomainToUiMapper(
        coreModule.resourceProvider,
        BaseReportDomainToUiMapper()
    )

    private fun communication() = ReportsCommunication.Base()
}