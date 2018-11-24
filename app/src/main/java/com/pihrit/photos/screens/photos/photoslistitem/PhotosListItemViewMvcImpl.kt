package com.pihrit.photos.screens.photos.photoslistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.pihrit.photos.R
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.views.BaseObservableViewMvc
import com.squareup.picasso.Picasso

class PhotosListItemViewMvcImpl(mInflater: LayoutInflater, mParent: ViewGroup?) :
    BaseObservableViewMvc<PhotosListItemViewMvc.Listener>(), PhotosListItemViewMvc {

    private lateinit var mPhoto: Photo
    private val mIvThumbnail: ImageView

    init {
        setRootView(mInflater.inflate(R.layout.layout_photo_list_item, mParent, false))

        mIvThumbnail = findViewById(R.id.ivThumbnail)

        getRootView().setOnClickListener {
            for (listener in getListeners()) {
                listener.onPhotoClicked(mPhoto)
            }
        }
    }

    override fun bindPhoto(photo: Photo) {
        mPhoto = photo

        Picasso.get()
            .load(mPhoto.thumbnailUrl)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(mIvThumbnail)
    }
}