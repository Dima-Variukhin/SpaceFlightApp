package com.example.spaceflightapp.presentation.reports

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.core.Show
import com.example.spaceflightapp.domain.reports.ReportsDomainToUiMapper
import com.example.spaceflightapp.domain.reports.ReportsInteractor
import com.example.spaceflightapp.presentation.BaseViewModel
import com.example.spaceflightapp.presentation.NavigationCommunicationWeb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReportsViewModel(
    private val reportsInteractor: ReportsInteractor,
    private val mapper: ReportsDomainToUiMapper<ReportsUi>,
    private val communication: ReportsCommunication,
    private val navigator: ReportsNavigator,
    private val navigationCommunicationWeb: NavigationCommunicationWeb,
    resourceProvider: ResourceProvider,
) : BaseViewModel(), Show {
    fun fetchReports() {
        communication.map(ReportsUi.Base(ArrayList(listOf(ReportUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = reportsInteractor.fetchReports()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun update() {
        communication.map(ReportsUi.Base(ArrayList(listOf(ReportUi.Progress))))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = reportsInteractor.update()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<ReportsUi>) {
        communication.observe(owner, observer)
    }

    fun initReports() {
        navigator.saveReportScreen()
        fetchReports()
    }

    override fun open(data: String) {
        navigationCommunicationWeb.map(data)
    }
}