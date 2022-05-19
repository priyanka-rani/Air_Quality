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
    fun getCity(query: String) = liveData {
        emit(Resource.loading())
        try {
            emit(Resource.success(apiService.getCityWiseAirQuality(city = query)))
        } catch (e: Exception) {
            emit(Resource.error(msg = e.message))
        }
    }

    suspend fun createGardenPlanting(station: Station) {
        stationDao.insert(station)
    }

    fun getPlant(query: String) = stationDao.getCities(query)

}