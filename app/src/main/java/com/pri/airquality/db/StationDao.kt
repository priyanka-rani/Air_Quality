package com.pri.airquality.db

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
    fun getCities(): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE city like :query")
    fun getCities(query: String): Flow<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: Station)
}
