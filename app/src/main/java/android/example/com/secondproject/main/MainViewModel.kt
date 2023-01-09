package android.example.com.secondproject.main

import android.app.Application
import android.example.com.secondproject.repositories.AsteroidListRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // private val database = getDatabase(application)
    private val asteroidListRepo = AsteroidListRepository(/*database*/)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    val model = asteroidListRepo.imageOfTheDay

    init {
        /*viewModelScope.launch {
            asteroidLisRepo.refreshAsteroidList(API_KEY = BuildConfig.API_KEY)
        }*/

        imageOfTheDay()
    }


    private fun imageOfTheDay() {
        viewModelScope.launch {
            asteroidListRepo.imageOfTheDay()
        }
    }
}