package android.example.com.secondproject.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageOfTheDayModel(
    val media_type: String,
    val url: String,
    val title: String
):Parcelable