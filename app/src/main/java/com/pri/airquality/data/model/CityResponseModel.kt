package com.pri.airquality.data.model

import androidx.room.ColumnInfo
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
    @PrimaryKey
    @field:SerializedName("_id")
    @ColumnInfo(name = "id")
    val id: String,
    @field:SerializedName("AQI")
    val aQI: String? = null,
    @field:SerializedName("aqiInfo")
    @Embedded
    val aqiInfo: AqiInfo? = null,
    @field:SerializedName("CO")
    val cO: String? = null,
    @field:SerializedName("city")
    val city: String? = null,
    @field:SerializedName("countryCode")
    val countryCode: String? = null,
    @field:SerializedName("division")
    val division: String? = null,
    @field:SerializedName("lat")
    val lat: String? = null,
    @field:SerializedName("lng")
    val lng: String? = null,
    @field:SerializedName("NO2")
    val nO2: String? = null,
    @field:SerializedName("OZONE")
    val oZONE: String? = null,
    @field:SerializedName("PM10")
    val pM10: String? = null,
    @field:SerializedName("PM25")
    val pM25: String? = null,
    @field:SerializedName("placeName")
    val placeName: String? = null,
    @field:SerializedName("postalCode")
    val postalCode: String? = null,
    @field:SerializedName("SO2")
    val sO2: String? = null,
    @field:SerializedName("state")
    val state: String? = null,
    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
) {
    override fun toString(): String {
        return city.takeUnless { it.isNullOrBlank() } ?: kotlin.run {
            division.takeUnless { it.isNullOrBlank() } ?: kotlin.run {
                state.takeUnless { it.isNullOrBlank() } ?: placeName.orEmpty()
            }
        }
    }
}

data class AqiInfo(
    @field:SerializedName("category")
    val category: String? = null,
    @field:SerializedName("concentration")
    val concentration: String? = null,
    @field:SerializedName("pollutant")
    val pollutant: String? = null
)