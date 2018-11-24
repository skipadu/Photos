package com.pihrit.photos.screens.photodetails

import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.views.ObservableViewMvc

interface PhotoDetailsViewMvc : ObservableViewMvc<PhotoDetailsViewMvc.Listener> {

    interface Listener {
        fun onNavigateUpClicked()
    }

    fun bindPhoto(photo: Photo)
}