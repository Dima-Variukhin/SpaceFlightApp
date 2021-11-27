package com.myapp.spaceflightapp.data.upcoming.cloud

import com.google.gson.internal.bind.util.ISO8601Utils
import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.upcoming.*
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpcomingCloudToDataMapper : Abstract.Mapper.Data<List<UpcomingCloud>, List<UpcomingData>> {
    override fun map(data: List<UpcomingCloud>): List<UpcomingData> {
        return data.map { map(it) }
    }

    private fun map(data: UpcomingCloud): UpcomingData {
        return UpcomingData(
            data.name,
            getStart(data.date),
            data.unix,
            data.datePrecision,
            data.upcoming,
            getImage(data),
            getRedditRecovery(data),
            getRedditCampaign(data),
            getWiki(data)
        )
    }

    private fun getImage(data: UpcomingCloud): String? {
        if (data.links.patch.large == null) return null
        return data.links.patch.large
    }

    private fun getRedditRecovery(data: UpcomingCloud): String? {
        return data.links.reddit.recovery
    }

    private fun getRedditCampaign(data: UpcomingCloud): String? {
        return data.links.reddit.campaign
    }

    private fun getWiki(data: UpcomingCloud): String? {
        if (data.links.wikipedia == null) return null
        return data.links.wikipedia
    }


    private fun getStart(data: String): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy' at 'HH:mm", Locale.US)
        val date = ISO8601Utils.parse(data, ParsePosition(0))
        return dateFormat.format(date)
    }
}