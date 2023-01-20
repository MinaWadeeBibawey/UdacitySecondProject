package android.udacity.com.udacitysecondproject.repositories

import android.content.Context
import android.udacity.com.udacitysecondproject.database.AsteroidEntity
import android.udacity.com.udacitysecondproject.database.AsteroidListDB
import android.udacity.com.udacitysecondproject.database.asDomainModel
import android.udacity.com.udacitysecondproject.models.Asteroid
import android.udacity.com.udacitysecondproject.network.AsteroidApiFilter
import android.udacity.com.udacitysecondproject.network.AsteroidListService
import android.udacity.com.udacitysecondproject.network.SafeApiCall
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AsteroidListRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: AsteroidListService,
    private val dataBase: AsteroidListDB
) : SafeApiCall {

    suspend fun imageOfTheDay(apiKey: String) = safeApiCall(
        {
            api.getImageOfTheDayAsync(apiKey)
        },
        context
    )

    suspend fun asteroidList(apiKey: String) =
        safeApiCall(
            {
                api.getAsteroidList(apiKey)
            },
            context
        )

    suspend fun saveAsteroidListLocally(asteroidList: AsteroidEntity) {
        dataBase.asteroidListDao.insertOrders(asteroidList)
    }

    fun getAsteroidListFromDB(filter: AsteroidApiFilter): LiveData<List<Asteroid>> =
        Transformations.map(
            when (filter.value) {
                "today" -> {
                    dataBase.asteroidListDao.getTodayAsteroid()
                }
                "seven_days" -> {
                    dataBase.asteroidListDao.getSevenDaysAsteroids()
                }
                else -> {
                    dataBase.asteroidListDao.getAsteroidList()
                }
            }
        ) {
            it.asDomainModel()
        }
}