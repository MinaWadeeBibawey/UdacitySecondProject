package android.udacity.com.udacitysecondproject.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(asteroidList: AsteroidEntity)

    @Query("SELECT * FROM asteroid_list ORDER BY date ASC")
    fun getAsteroidList(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid_list WHERE date BETWEEN strftime('%Y-%m-%d', 'now') AND strftime('%Y-%m-%d','now','6 days')")
    fun getSevenDaysAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid_list WHERE date = strftime('%Y-%m-%d', 'now')")
    fun getTodayAsteroid(): LiveData<List<AsteroidEntity>>
}