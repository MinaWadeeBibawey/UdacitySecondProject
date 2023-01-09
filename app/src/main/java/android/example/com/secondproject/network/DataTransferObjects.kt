/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package android.example.com.secondproject.network

import android.example.com.secondproject.PictureOfDay
import android.example.com.secondproject.models.ImageOfTheDayModel
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */

@JsonClass(generateAdapter = true)
data class ImageOfTheDay(
    val media_type: String,
    val url: String,
    val title: String
)

fun ImageOfTheDay.asDomainModel(): PictureOfDay {
    return PictureOfDay(
        mediaType = media_type,
        url = url,
        title = title
    )
}