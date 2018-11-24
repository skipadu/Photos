package com.pihrit.photos.photos

import java.io.Serializable

class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Serializable
