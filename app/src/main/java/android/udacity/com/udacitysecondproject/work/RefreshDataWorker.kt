package android.udacity.com.udacitysecondproject.work

import android.content.Context
import android.udacity.com.udacitysecondproject.ui.main.MainViewModel
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.HttpException
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
    val viewModel: MainViewModel
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     * Note: In recent work version upgrade, 1.0.0-alpha12 and onwards have a breaking change.
     * The doWork() function now returns Result instead of Payload because they have combined Payload into Result.
     * Read more here - https://developer.android.com/jetpack/androidx/releases/work#1.0.0-alpha12
     */
    override suspend fun doWork(): Result {
        return try {
            viewModel.refreshAsteroidList()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
