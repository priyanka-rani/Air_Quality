package com.pri.airquality.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pri.airquality.model.Station
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Station class.
 */
@Dao
interface StationDao {
    @Query("SELECT * FROM station ORDER BY city")
    fun getStations(): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE city LIKE :query OR division LIKE :query OR placeName LIKE :query OR state LIKE :query")
    fun getStationsByCity(query: String): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE id=:id")
    fun getStationsById(id: String): LiveData<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: List<Station>)
}
