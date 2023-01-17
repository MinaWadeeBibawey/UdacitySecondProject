package android.udacity.com.udacitysecondproject.di

import android.app.Application
import android.udacity.com.udacitysecondproject.network.AsteroidListService
import android.udacity.com.udacitysecondproject.network.Network
import androidx.room.Room
import android.udacity.com.udacitysecondproject.database.AsteroidListDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFinishedOrderDetailsApi(remoteDataSource: Network): AsteroidListService {
        return remoteDataSource.buildApi(AsteroidListService::class.java)
    }

    @Provides
    @Singleton
    fun provideAsteroidListDatabase(app: Application): AsteroidListDB {
        return Room.databaseBuilder(
            app,
            AsteroidListDB::class.java,
            "asteroid_list_saved_DB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}