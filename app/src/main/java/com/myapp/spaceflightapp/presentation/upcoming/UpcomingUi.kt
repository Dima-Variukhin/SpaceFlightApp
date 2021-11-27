package com.myapp.spaceflightapp.presentation.upcoming

import com.myapp.spaceflightapp.presentation.launches.LaunchUi


sealed class UpcomingUi<T> {
    abstract val value: T

    data class Name(override val value: String) : UpcomingUi<String>()
    data class LaunchDate(override val value: String) : UpcomingUi<String>()
    data class Unix(override val value: Int) : UpcomingUi<Int>()
    data class DatePrecision(override val value: String) : UpcomingUi<String>()
    data class Upcoming(override val value: Boolean) : UpcomingUi<Boolean>()
    data class Image(override val value: String) : UpcomingUi<String>()
    data class RedditRecovery(override val value: String) : UpcomingUi<String>()
    data class RedditCampaign(override val value: String) : UpcomingUi<String>()
    data class Wiki(override val value: String) : UpcomingUi<String>()
}