package android.example.com.secondproject.workManager

/*
import android.content.Context
import android.example.com.secondproject.BuildConfig
import android.example.com.secondproject.database.getDatabase
import android.example.com.secondproject.repositories.AsteroidListRepository
import androidx.navigation.ui.AppBarConfiguration
import androidx.work.Configuration
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.HttpException


class RefreshData(appContext: Context, parameters: WorkerParameters) :
    CoroutineWorker(appContext, parameters) {

    companion object {
        const val WORK_NAME = "RefreshAsteroidRadar"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidListRepository(database)
        try {
            repository.refreshAsteroidList(API_KEY = BuildConfig.API_KEY)
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()

    }
}*/
