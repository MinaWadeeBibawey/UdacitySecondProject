package android.udacity.com.udacitysecondproject.database

import android.udacity.com.udacitysecondproject.models.Asteroid
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_list")
data class AsteroidEntity constructor(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "codename")
    val codename: String,
    @ColumnInfo(name = "date")
    val closeApproachDate: String,
    @ColumnInfo(name = "absoluteMagnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimatedDiameter")
    val estimatedDiameter: Double,
    @ColumnInfo(name = "relativeVelocity")
    val relativeVelocity: Double,
    @ColumnInfo(name = "distanceFromEarth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "isPotentiallyHazardous")
    val isPotentiallyHazardous: Boolean
)

fun List<AsteroidEntity>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }
}
