package com.pihrit.photos.networking

import com.pihrit.photos.networking.photos.PhotosResponseSchema
import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholderAPI {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("/photos")
    fun getPhotos(): Call<List<PhotosResponseSchema>>
}