package com.pri.airquality.ui.search

import androidx.lifecycle.*
import com.pri.airquality.data.model.Station
import com.pri.airquality.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {
    private val _cityApi = MutableLiveData<String>()
    private val _query = MutableStateFlow("")
    var apiObserved = false

    val stationResponse = _cityApi.switchMap {
        repository.getCityWiseAirQuality(it)
    }

    val searchResult = _query.debounce(300).flatMapLatest {
        repository.getStationsByCity(query = it)
    }.asLiveData()

    fun searchCity(query: String) {
        _query.value = query
    }

    fun callAirQualityApi(city: String?) {
        city?.let {
            apiObserved = false
            _cityApi.value = it
        }
    }

    fun insertStations(station: List<Station>) {
        viewModelScope.launch {
            repository.insertStation(station)
        }
    }
}