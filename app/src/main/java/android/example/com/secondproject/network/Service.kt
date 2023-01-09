package android.example.com.secondproject.network

import android.example.com.secondproject.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class AsteroidLisFilter(val value: String) { TODAY(""), NEXT_SEVEN_DAYS(""), SHOW_ALL("") }

interface AsteroidListService {
    @GET("devbytes.json")
    fun getAsteroidList(
        @Query("start_date") startDate: String = "",
        @Query("end_date") EndDate: String = "",
        @Query("api_key") API_KEY: String
    ): String

    @GET("planetary/apod")
    suspend fun getImageOfTheDayAsync(@Query("api_key") API_KEY: String): ImageOfTheDay
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    val service = retrofit.create(AsteroidListService::class.java)
}

/*object AsteroidApi {
    val retrofitService : AsteroidListService by lazy { Network.retrofit.create(AsteroidListService::class.java) }
}*/