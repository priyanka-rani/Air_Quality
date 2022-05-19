package com.pri.airquality.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CityResponseModel(
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("stations")
    val stations: List<Station>? = null
)

@Entity
data class Station(
    @field:SerializedName("AQI")
    val aQI: Int? = null,
    @field:SerializedName("aqiInfo")
    @Embedded
    val aqiInfo: AqiInfo? = null,
    @field:SerializedName("CO")
    val cO: Double? = null,
    @field:SerializedName("city")
    val city: String? = null,
    @field:SerializedName("countryCode")
    @PrimaryKey
    val countryCode: String,
    @field:SerializedName("division")
    val division: String? = null,
    @field:SerializedName("lat")
    val lat: Double? = null,
    @field:SerializedName("lng")
    val lng: Double? = null,
    @field:SerializedName("NO2")
    val nO2: Double? = null,
    @field:SerializedName("OZONE")
    val oZONE: Double? = null,
    @field:SerializedName("PM10")
    val pM10: Double? = null,
    @field:SerializedName("PM25")
    val pM25: Double? = null,
    @field:SerializedName("placeName")
    val placeName: String? = null,
    @field:SerializedName("postalCode")
    val postalCode: String? = null,
    @field:SerializedName("SO2")
    val sO2: Double? = null,
    @field:SerializedName("state")
    val state: String? = null,
    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class AqiInfo(
    @field:SerializedName("category")
    val category: String? = null,
    @field:SerializedName("concentration")
    val concentration: Double? = null,
    @field:SerializedName("pollutant")
    val pollutant: String? = null
)