package com.myapp.spaceflightapp.sl.reports

import com.myapp.spaceflightapp.data.reports.ReportRepository
import com.myapp.spaceflightapp.domain.reports.BaseReportDataToDomainMapper
import com.myapp.spaceflightapp.domain.reports.BaseReportsDataToDomainMapper
import com.myapp.spaceflightapp.domain.reports.ReportsInteractor
import com.myapp.spaceflightapp.presentation.reports.BaseReportDomainToUiMapper
import com.myapp.spaceflightapp.presentation.reports.BaseReportsDomainToUiMapper
import com.myapp.spaceflightapp.presentation.reports.ReportsCommunication
import com.myapp.spaceflightapp.presentation.reports.ReportsViewModel
import com.myapp.spaceflightapp.sl.core.BaseModule
import com.myapp.spaceflightapp.sl.core.CoreModule

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
            coreModule.navigationCommunicationShare,
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