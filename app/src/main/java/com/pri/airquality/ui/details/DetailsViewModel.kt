package com.pri.airquality.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.pri.airquality.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SearchRepository
) : ViewModel() {
    private val stationId: String = savedStateHandle.get<String>(STATON_ID_SAVED_STATE_KEY)!!
    val stationDetails = repository.getStationsById(stationId).map { station ->
        with(station) {
            listOf(
                "CO" to cO,
                "NO2" to nO2,
                "OZONE" to oZONE,
                "PM10" to pM10,
                "PM25" to pM25,
                "SO2" to sO2,
                "AQI" to aQI,
                "Pollutant" to aqiInfo?.pollutant,
                "Concentration" to aqiInfo?.concentration,
                "Category" to aqiInfo?.category,
                "Place Name" to placeName,
                "Postal Code" to postalCode,
                "City" to city,
                "Division" to division,
                "State" to state,
                "Country Code" to countryCode,
                "Lat" to lat,
                "Long" to lng,
                "Updated at" to updatedAt
            )
        }
    }

    companion object {
        private const val STATON_ID_SAVED_STATE_KEY = "id"
    }
}