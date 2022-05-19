package com.pri.airquality.api

import com.pri.airquality.BuildConfig
import com.pri.airquality.model.CityResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("x-api-key : ${BuildConfig.API_KEY}")
    @GET("/latest/by-city")
    suspend fun getCityWiseAirQuality(@Query("city") city: String): CityResponseModel?
}