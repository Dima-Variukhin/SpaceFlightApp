package com.myapp.spaceflightapp.data.upcoming.cloud

import com.google.gson.annotations.SerializedName

data class UpcomingCloud(
    @SerializedName("name")
    val name: String,
    @SerializedName("date_utc")
    val date: String,
    @SerializedName("date_unix")
    val unix: Int,
    @SerializedName("date_precision")
    val datePrecision: String,
    @SerializedName("upcoming")
    val upcoming: Boolean,
    @SerializedName("links")
    val links: Links
)

data class Links(
    @SerializedName("patch")
    val patch: Patch,
    @SerializedName("reddit")
    val reddit: Reddit,
    @SerializedName("wikipedia")
    val wikipedia: String?,
)

data class Patch(
    @SerializedName("large")
    val large: String?,
)

data class Reddit(
    @SerializedName("campaign")
    val campaign: String?,
    @SerializedName("recovery")
    val recovery: String?
)
