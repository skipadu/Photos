package com.pihrit.photos.networking.photos

import com.squareup.moshi.Json

data class PhotosResponseSchema(
    @Json(name = "albumId")
    val albumId: Int,
    @Json(name = "albumId")
    val id: Int,
    @Json(name = "albumId")
    val title: String,
    @Json(name = "albumId")
    val url: String,
    @Json(name = "albumId")
    val thumbnailUrl: String
)
