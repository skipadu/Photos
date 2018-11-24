package com.pihrit.photos.screens.photos.photoslistitem

import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.views.ObservableViewMvc

interface PhotosListItemViewMvc : ObservableViewMvc<PhotosListItemViewMvc.Listener> {

    interface Listener {
        fun onPhotoClicked(photo: Photo)
    }

    fun bindPhoto(photo: Photo)
}