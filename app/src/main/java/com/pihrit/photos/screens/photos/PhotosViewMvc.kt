package com.pihrit.photos.screens.photos

import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.views.ObservableViewMvc

interface PhotosViewMvc : ObservableViewMvc<PhotosViewMvc.Listener> {

    interface Listener {
        fun onPhotoClicked(photo: Photo)
        fun onPullToRefresh()
    }

    fun bindPhotos(photos: List<Photo>)
    fun showProgressIndication()
    fun hideProgressIndication()
}