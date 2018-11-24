package com.pihrit.photos.photos

import com.pihrit.photos.common.BaseObservable
import com.pihrit.photos.networking.JSONPlaceholderAPI
import com.pihrit.photos.networking.photos.PhotosResponseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchPhotosUseCase(private val mJSONPlaceholderAPI: JSONPlaceholderAPI) :
    BaseObservable<FetchPhotosUseCase.Listener>() {

    interface Listener {
        fun onPhotosFetched(photos: ArrayList<Photo>)
        fun onPhotosFetchFailed()
    }

    fun fetchPhotosAndNotify() {
        mJSONPlaceholderAPI.getPhotos()
            .enqueue(object : Callback<List<PhotosResponseSchema>> {
                override fun onResponse(
                    call: Call<List<PhotosResponseSchema>>,
                    response: Response<List<PhotosResponseSchema>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { notifySuccess(it) }
                    } else {
                        notifyFailure()
                    }
                }

                override fun onFailure(call: Call<List<PhotosResponseSchema>>, t: Throwable) {
                    notifyFailure()
                }
            })
    }

    private fun notifySuccess(photosResponseSchemas: List<PhotosResponseSchema>) {
        val photos = ArrayList<Photo>(photosResponseSchemas.size)
        for (p in photosResponseSchemas) {
            photos.add(
                Photo(
                    p.albumId,
                    p.id,
                    p.title,
                    p.url,
                    p.thumbnailUrl
                )
            )
        }
        for (listener in listeners) {
            listener.onPhotosFetched(photos)
        }
    }

    private fun notifyFailure() {
        for (listener in listeners) {
            listener.onPhotosFetchFailed()
        }
    }
}