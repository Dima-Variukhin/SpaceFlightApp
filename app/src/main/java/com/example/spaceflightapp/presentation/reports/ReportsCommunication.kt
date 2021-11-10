package com.example.spaceflightapp.presentation.reports

import com.example.spaceflightapp.core.Communication

interface ReportsCommunication : Communication<ReportsUi> {
    class Base : Communication.Base<ReportsUi>(), ReportsCommunication
}