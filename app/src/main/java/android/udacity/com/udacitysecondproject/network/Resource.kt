package android.udacity.com.udacitysecondproject.network

import android.udacity.com.udacitysecondproject.models.ErrorModel

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ErrorModel?) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}