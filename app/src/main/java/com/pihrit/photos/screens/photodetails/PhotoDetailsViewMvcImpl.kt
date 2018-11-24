package com.pihrit.photos.screens.photodetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.pihrit.photos.R
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.toolbar.ToolbarViewMvc
import com.pihrit.photos.screens.common.views.BaseObservableViewMvc
import com.pihrit.photos.screens.common.views.ViewMvcFactory
import com.squareup.picasso.Picasso

class PhotoDetailsViewMvcImpl(mInflater: LayoutInflater, mParent: ViewGroup?, mViewMvcFactory: ViewMvcFactory) :
    PhotoDetailsViewMvc, BaseObservableViewMvc<PhotoDetailsViewMvc.Listener>() {

    private val mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: Toolbar

    private lateinit var mPhoto: Photo
    private val mPhotoImageView: ImageView
    private val mTitleTextView: TextView

    init {
        setRootView(mInflater.inflate(R.layout.layout_photo_details, mParent, false))

        mPhotoImageView = findViewById(R.id.ivPhotoDetailImage)
        mTitleTextView = findViewById(R.id.tvPhotoDetailTitle)

        mToolbar = findViewById(R.id.toolbar)
        mToolbarViewMvc = mViewMvcFactory.getToolbarViewMvc(mToolbar)
        initToolbar()
    }

    private fun initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView())
        mToolbarViewMvc.setTitle(getContext().getString(R.string.toolbar_title_photodetails))
        mToolbarViewMvc.enableUpButtonAndListen(object : ToolbarViewMvc.NavigateUpClickListener {
            override fun onNavigateUpClicked() {
                for (listener in getListeners()) {
                    listener.onNavigateUpClicked()
                }
            }
        })

    }

    override fun bindPhoto(photo: Photo) {
        mPhoto = photo

        Picasso.get()
            .load(mPhoto.url)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(mPhotoImageView)

        mTitleTextView.text = mPhoto.title
    }

}