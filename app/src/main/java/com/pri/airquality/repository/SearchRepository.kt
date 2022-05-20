package com.pri.airquality.repository

import androidx.lifecycle.liveData
import com.pri.airquality.api.ApiService
import com.pri.airquality.db.StationDao
import com.pri.airquality.model.Resource
import com.pri.airquality.model.Station
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: ApiService,
    private val stationDao: StationDao
) {
    fun getCityWiseAirQuality(query: String) = liveData {
        emit(Resource.loading())
        try {
            val response = apiService.getCityWiseAirQuality(city = query)
            emit(Resource.success(response?.stations, response?.message))
        } catch (e: Exception) {
            emit(Resource.error(msg = e.message))
        }
    }

    suspend fun insertStation(station: List<Station>) {
        stationDao.insert(station)
    }

    fun getStationsByCity(query: String) = stationDao.getStationsByCity("%$query%")

    fun getStationsById(id: String) = stationDao.getStationsById(id)

}