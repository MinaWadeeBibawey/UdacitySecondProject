package android.example.com.secondproject.repositories

import android.example.com.secondproject.BuildConfig
import android.example.com.secondproject.models.ImageOfTheDayModel
import android.example.com.secondproject.network.Network
import android.example.com.secondproject.network.asDomainModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidListRepository {


    /* suspend fun refreshAsteroidList(
         startDate: String? = "",
         ednDate: String? = "",
         API_KEY: String
     ) {
         withContext(Dispatchers.IO) {
             val asteroidList = Network.devbytes.getAsteroidList(
                 startDate,
                 ednDate,
                 API_KEY
             )
             Network.devbytes.getImageOfTheDay(API_KEY)
             //  dataBase.asteroidDao.insertAll(*asteroidList)
         }
     }*/

    private val initModel = MutableLiveData<ImageOfTheDayModel>()
    val imageOfTheDay: LiveData<ImageOfTheDayModel> get() = initModel


    suspend fun imageOfTheDay() {
        withContext(Dispatchers.IO) {
            val model = Network.service.getImageOfTheDayAsync(BuildConfig.API_KEY).await()
            initModel.value = model.asDomainModel()
        }
    }
}