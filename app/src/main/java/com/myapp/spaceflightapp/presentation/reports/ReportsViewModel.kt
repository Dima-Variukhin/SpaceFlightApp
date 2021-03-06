package com.myapp.spaceflightapp.presentation.reports

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.core.Show
import com.myapp.spaceflightapp.domain.reports.ReportsDomainToUiMapper
import com.myapp.spaceflightapp.domain.reports.ReportsInteractor
import com.myapp.spaceflightapp.presentation.BaseViewModel
import com.myapp.spaceflightapp.presentation.NavigationCommunicationShare
import com.myapp.spaceflightapp.presentation.NavigationCommunicationWeb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReportsViewModel(
    private val reportsInteractor: ReportsInteractor,
    private val mapper: ReportsDomainToUiMapper<ReportsUi>,
    private val communication: ReportsCommunication,
    private val navigator: ReportsNavigator,
    private val navigationCommunicationWeb: NavigationCommunicationWeb,
    private val navigationCommunicationShare: NavigationCommunicationShare,
) : BaseViewModel(), Show {
    fun fetchReports() {
        communication.map(ReportsUi.Base(ArrayList(listOf(ReportUi.Progress))))
        viewModelScope.debounceLaunch(300) {
            withContext(Dispatchers.IO) {
                val resultDomain = reportsInteractor.fetchReports()
                val resultUi = resultDomain.map(mapper)
                withContext(Dispatchers.Main) {
                    communication.map(resultUi)
                }
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

    override fun share(data: String) {
        navigationCommunicationShare.map(data)
    }

    override fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            reportsInteractor.changeFavorite(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt,
            )
        }
    }
}