package com.pihrit.photos.screens.common.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.pihrit.photos.screens.common.toolbar.ToolbarViewMvc
import com.pihrit.photos.screens.photodetails.PhotoDetailsViewMvc
import com.pihrit.photos.screens.photodetails.PhotoDetailsViewMvcImpl
import com.pihrit.photos.screens.photos.PhotosViewMvc
import com.pihrit.photos.screens.photos.PhotosViewMvcImpl
import com.pihrit.photos.screens.photos.photoslistitem.PhotosListItemViewMvc
import com.pihrit.photos.screens.photos.photoslistitem.PhotosListItemViewMvcImpl
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val mLayoutInflater: LayoutInflater) {

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(mLayoutInflater, parent)
    }

    fun <T : ViewMvc> newInstance(mvcViewClass: Class<T>, @Nullable container: ViewGroup?): T {

        val viewMvc: ViewMvc = when (mvcViewClass) {
            PhotosViewMvc::class.java -> PhotosViewMvcImpl(mLayoutInflater, container, this)
            PhotosListItemViewMvc::class.java -> PhotosListItemViewMvcImpl(mLayoutInflater, container)
            PhotoDetailsViewMvc::class.java -> PhotoDetailsViewMvcImpl(mLayoutInflater, container, this)
            else -> throw IllegalArgumentException("Unsupported MVC-view class $mvcViewClass")
        }
        @Suppress("UNCHECKED_CAST")
        return viewMvc as T
    }

}