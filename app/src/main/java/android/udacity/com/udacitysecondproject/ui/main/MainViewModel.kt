package android.udacity.com.udacitysecondproject.ui.main

import android.udacity.com.udacitysecondproject.BuildConfig
import android.udacity.com.udacitysecondproject.database.AsteroidEntity
import android.udacity.com.udacitysecondproject.models.Asteroid
import android.udacity.com.udacitysecondproject.models.PictureOfDay
import android.udacity.com.udacitysecondproject.network.AsteroidApiFilter
import android.udacity.com.udacitysecondproject.network.Resource
import android.udacity.com.udacitysecondproject.repositories.AsteroidListRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: AsteroidListRepository) :
    ViewModel() {

    private val _pictureOfDay: MutableLiveData<Resource<PictureOfDay>> = MutableLiveData()
    val pictureOfDay: LiveData<Resource<PictureOfDay>> get() = _pictureOfDay

    private val _asteroidList: MutableLiveData<Resource<String>> = MutableLiveData()
    val asteroidList: LiveData<Resource<String>> get() = _asteroidList

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails: LiveData<Asteroid?> get() = _navigateToAsteroidDetails

    init {
        imageOfTheDay()
    }

    fun navigateToAsteroidDetails(asteroid: Asteroid) {
        _navigateToAsteroidDetails.value = asteroid
    }

    fun asteroidDetailsNavigationComplete() {
        _navigateToAsteroidDetails.value = null
    }

    private fun imageOfTheDay() = viewModelScope.launch {
        _pictureOfDay.postValue(repo.imageOfTheDay(BuildConfig.API_KEY))
    }

    fun refreshAsteroidList() = viewModelScope.launch {
        _asteroidList.postValue(repo.asteroidList(BuildConfig.API_KEY))
    }

    fun saveAsteroidListLocally(asteroidList: AsteroidEntity) = viewModelScope.launch {
        repo.saveAsteroidListLocally(asteroidList)
    }

    fun getAsteroidListFromDB(filter:AsteroidApiFilter) =  repo.getAsteroidListFromDB(filter)

}

