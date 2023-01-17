package android.udacity.com.udacitysecondproject.network

import android.content.Context
import android.udacity.com.udacitysecondproject.models.ErrorModel
import android.udacity.com.udacitysecondproject.models.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T, context: Context
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
//                        val jObjError = JSONObject(throwable.response()?.errorBody()?.string().toString())
                        Resource.Failure(
                            false,
                            throwable.code(),
                            ErrorModel(Status(throwable.code(), throwable.message()))
                        )
                    }
                    is IOException -> {
                        Resource.Failure(true, null,
                            throwable.message?.let {
                                ErrorModel(Status(400, it))
                            })
                    }
                    else -> {
                        Resource.Failure(networkAvailable(context), null,
                            throwable.message?.let { ErrorModel(Status(400, it)) })
                    }
                }
            }
        }
    }
}