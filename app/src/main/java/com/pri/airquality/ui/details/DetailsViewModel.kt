package com.pri.airquality.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.pri.airquality.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {
    fun getStationDetails(id: String) = repository.getStationsById(id).map {
        it.run {
            buildList {
                add("Time" to updatedAt)
                add("CO" to cO)
                add("NO2" to nO2)
                add("OZONE" to oZONE)
                add("PM10" to pM10)
                add("PM25" to pM25)
                add("SO2" to sO2)
                add("AQI" to aQI)
                add("Pollutant" to aqiInfo?.pollutant)
                add("Concentration" to aqiInfo?.concentration)
                add("Category" to aqiInfo?.category)
                add("Place Name" to placeName)
                add("Postal Code" to postalCode)
                add("City" to city)
                add("Division" to division)
                add("State" to state)
                add("Country Code" to countryCode)
                add("Lat" to lat)
                add("Long" to lng)
            }
        }
    }
}