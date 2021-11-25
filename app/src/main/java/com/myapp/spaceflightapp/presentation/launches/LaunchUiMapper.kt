package com.myapp.spaceflightapp.presentation.launches

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.launches.LaunchData

class LaunchUiMapper : Abstract.Mapper.Data<LaunchData, List<LaunchUi<*>>> {
    override fun map(data: LaunchData) =
        ArrayList<LaunchUi<*>>().apply {
            add(LaunchUi.MissionName(data.missionName))
            val cores = data.rocket.firstStage.cores
            if (cores.isNotEmpty()) {
                val firstStageData = ArrayList<Pair<String, Boolean>>().apply {
                    for (core in cores) {
                        add(Pair(core.coreSerial, core.reused))
                    }
                }
                add(
                    LaunchUi.Rocket(
                        data.rocket.name,
                        data.rocket.type,
                        firstStageData,
                        data.rocket.secondStage
                    )
                )
            }
            add(LaunchUi.FlightNumber(data.flightNumber.toString()))
            add(LaunchUi.LaunchPlace(data.launchPlace))
            add(LaunchUi.LaunchYear(data.launchYear.toString()))
            if (!data.launchDate.isNullOrEmpty())
                add(LaunchUi.LaunchDate(data.launchDate))
            add(LaunchUi.LaunchSuccess(data.launchSuccess))
            add(LaunchUi.MissionName("Details"))
            if (!data.details.isNullOrEmpty())
                add(LaunchUi.Details(data.details))
            add(LaunchUi.MissionName("Ship info"))
            if (data.ships.isNotEmpty()) {
                var ships = ""
                for (ship in data.ships) ships += ship + "\n"
                add(LaunchUi.Ships(ships))
            }
            add(LaunchUi.MissionName("Social networking"))
            data.links.forEach {
                add(LaunchUi.LinkTitle(it.title, it.address))
            }
            data.images.forEach {
                add(LaunchUi.Image(it.address))
            }
            data.PDFs.forEach {
                add(LaunchUi.PDF(it.title, it.address))
            }
        }
}