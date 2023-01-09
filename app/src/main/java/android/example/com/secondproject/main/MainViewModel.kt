package android.example.com.secondproject.main

import android.app.Application
import android.example.com.secondproject.repositories.AsteroidListRepository
import androidx.lifecycle.*
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


    class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}