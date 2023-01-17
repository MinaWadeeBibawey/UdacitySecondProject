package android.udacity.com.udacitysecondproject.network

import android.udacity.com.udacitysecondproject.models.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidListService {
    @GET("planetary/apod")
    suspend fun getImageOfTheDayAsync(@Query("api_key") API_KEY: String): PictureOfDay


    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidList(
        @Query("api_key") API_KEY: String
    ): String
}