package com.pihrit.photos.screens.photos

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.views.ViewMvcFactory
import com.pihrit.photos.screens.photos.photoslistitem.PhotosListItemViewMvc

class PhotosRecyclerAdapter(private val mListener: Listener, private val mViewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<PhotosViewHolder>(), PhotosListItemViewMvc.Listener {

    private var mPhotos: List<Photo> = ArrayList()

    interface Listener {
        fun onPhotoClicked(photo: Photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val viewMvc = mViewMvcFactory.newInstance(PhotosListItemViewMvc::class.java, null)

        viewMvc.registerListener(this)
        return PhotosViewHolder(viewMvc)
    }

    override fun getItemCount(): Int {
        return mPhotos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.mViewMvc.bindPhoto(mPhotos[position])
    }

    override fun onPhotoClicked(photo: Photo) {
        mListener.onPhotoClicked(photo)
    }

    fun bindPhotos(photos: List<Photo>) {
        mPhotos = ArrayList(photos)
        notifyDataSetChanged()
    }

}