package android.udacity.com.udacitysecondproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AsteroidEntity::class], version = 1, exportSchema = false)
abstract class AsteroidListDB : RoomDatabase() {

    abstract val asteroidListDao: AsteroidListDao
}