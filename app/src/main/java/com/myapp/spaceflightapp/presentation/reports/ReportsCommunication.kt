package com.myapp.spaceflightapp.presentation.reports

import com.myapp.spaceflightapp.core.Communication

interface ReportsCommunication : Communication<ReportsUi> {
    class Base : Communication.Base<ReportsUi>(), ReportsCommunication
}