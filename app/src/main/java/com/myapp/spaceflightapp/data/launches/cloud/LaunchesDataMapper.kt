package com.myapp.spaceflightapp.data.launches.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.launches.*
import com.google.gson.internal.bind.util.ISO8601Utils
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LaunchesDataMapper : Abstract.Mapper.Data<List<LaunchesCloud>, List<LaunchData>> {
    override fun map(data: List<LaunchesCloud>): List<LaunchData> {
        return data.map { map(it) }
    }

    private fun map(data: LaunchesCloud): LaunchData {
        return LaunchData(
            data.flightNumber,
            data.missionName,
            data.launchYear,
            getDate(data.launchDateUTC),
            getRocketData(data.rocket),
            data.ships,
            makeLinks(data.telemetry),
            data.reuse,
            data.launchSite.longName,
            data.launchSuccess,
            getPureLinks(data.links),
            getImages(data.links),
            getPDFs(data.links),
            data.details,
            data.upcoming,
            getDate(data.staticFireDateUTC),
            data.timeline
        )
    }

    private fun getRocketData(rocket: RocketCloud): RocketData {
        val firstStage =
            FirstStageData(rocket.firstStage.cores.map { CoreData(it.coreSerial, it.reused) })
        val secondStage = SecondStageData(
            rocket.secondStage.block,
            rocket.secondStage.payloads.map {
                PayloadData(
                    it.manufacturer,
                    it.nationality,
                    it.payloadType,
                    it.payloadMassKg,
                    it.orbit
                )
            })
        return RocketData(rocket.name, rocket.type, firstStage, secondStage)
    }

    private fun getPDFs(data: Map<String, Any>): List<PDFLink> {
        val list = ArrayList<PDFLink>()
        data.forEach { (key, value) ->
            if (value is String && value.endsWith(".pdf"))
                list.add(PDFLink(key, value))
        }
        return list
    }

    private fun getPureLinks(data: Map<String, Any>): List<Link> {
        val list = ArrayList<Link>(1)
        data.forEach { (key, value) ->
            if (value is String && key != "youtube_id") {
                if (!value.endsWith(".png") &&
                    !value.endsWith(".pdf") &&
                    !value.endsWith(".jpg") &&
                    !value.endsWith(".jpeg")
                )
                    list.add(Link(key, value))
            }
        }
        return list
    }

    private fun getImages(data: Map<String, Any>): List<ImageLink> {
        val list = ArrayList<ImageLink>()
        data.forEach { (_, value) ->
            if (value is String) {
                if (value.endsWith(".png") ||
                    value.endsWith(".jpg") ||
                    value.endsWith(".jpeg")
                )
                    list.add(ImageLink(value))
            } else if (value is List<*>) {
                value.forEach {
                    if (it is String && (it.endsWith(".png") ||
                                it.endsWith(".jpg") ||
                                it.endsWith(".jpeg"))
                    )
                        list.add(ImageLink(it))
                }
            }
        }
        return list
    }

    private fun makeLinks(data: Map<String?, String?>): List<Link> {
        val list = ArrayList<Link>(data.size)
        data.forEach { (key, value) ->
            if (!key.isNullOrEmpty() && !value.isNullOrEmpty())
                list.add(Link(key, value))
        }
        return list
    }

    private fun getDate(data: String?): String? {
        if (data == null) return null
        val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy' at 'HH:mm", Locale.US)
        val date = ISO8601Utils.parse(data, ParsePosition(0))
        return dateFormat.format(date)
    }
}