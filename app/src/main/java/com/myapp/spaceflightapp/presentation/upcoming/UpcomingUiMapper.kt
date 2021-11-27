package com.myapp.spaceflightapp.presentation.upcoming

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.upcoming.UpcomingData

class UpcomingUiMapper : Abstract.Mapper.Data<UpcomingData, List<UpcomingUi<*>>> {
    override fun map(data: UpcomingData) =
        ArrayList<UpcomingUi<*>>().apply {
            add(UpcomingUi.Name(data.name))
            add(UpcomingUi.LaunchDate(data.launchDate))
            add(UpcomingUi.DatePrecision(data.datePrecision))
            add(UpcomingUi.Upcoming(data.upcoming))
            add(UpcomingUi.Unix(data.unix))
            if (!data.image.isNullOrEmpty()) {
                add(UpcomingUi.Image(data.image))
            }
            if (!data.redditRecovery.isNullOrEmpty()) {
                add(UpcomingUi.RedditRecovery(data.redditRecovery))
            }
            if (!data.redditCampaign.isNullOrEmpty()) {
                add(UpcomingUi.RedditCampaign(data.redditCampaign))
            }
            if (!data.wiki.isNullOrEmpty()) {
                add(UpcomingUi.Wiki(data.wiki))
            }
        }
}