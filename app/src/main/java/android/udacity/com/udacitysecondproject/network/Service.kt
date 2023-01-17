package android.udacity.com.udacitysecondproject.network

import android.udacity.com.udacitysecondproject.util.Constants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

enum class AsteroidApiFilter(val value:String){ SHOW_TODAY("today"),SHOW_SEVEN_DAYS("seven_days"),SHOW_ALL("all") }



class Network @Inject constructor(){

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()
    // Configure retrofit to parse JSON and use coroutines
    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(api)
    }
}
