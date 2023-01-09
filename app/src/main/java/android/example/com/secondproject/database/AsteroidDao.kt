package android.example.com.secondproject.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

/*
@Dao
interface AsteroidDao {

    @Query("select * from asteroid_list_table")
    fun getAsteroid(): LiveData<AsteroidList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroidList: AsteroidList)

}

@Database(entities = [AsteroidList::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDatabase::class.java,
                "asteroidList"
            ).build()
        }
    }
    return INSTANCE
}*/
