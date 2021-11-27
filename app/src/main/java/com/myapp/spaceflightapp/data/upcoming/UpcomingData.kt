package com.myapp.spaceflightapp.data.upcoming


data class UpcomingData(
    val name: String,
    val launchDate: String,
    val unix: Int,
    val datePrecision: String,
    val upcoming: Boolean,
    val image: String?,
    val redditRecovery: String?,
    val redditCampaign: String?,
    val wiki: String?,
)

