package com.pihrit.photos.screens.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pihrit.photos.R
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.toolbar.ToolbarViewMvc
import com.pihrit.photos.screens.common.views.BaseObservableViewMvc
import com.pihrit.photos.screens.common.views.ViewMvcFactory

class PhotosViewMvcImpl(mInflater: LayoutInflater, mParent: ViewGroup?, mViewMvcFactory: ViewMvcFactory) :
    PhotosViewMvc,
    BaseObservableViewMvc<PhotosViewMvc.Listener>(), PhotosRecyclerAdapter.Listener,
    SwipeRefreshLayout.OnRefreshListener {

    private val mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: Toolbar

    private val mRecyclerPhotos: RecyclerView
    private val mAdapterPhotos: PhotosRecyclerAdapter
    private val mSwipeRefreshLayout: SwipeRefreshLayout

    init {
        setRootView(mInflater.inflate(R.layout.layout_photos, mParent, false))

        mRecyclerPhotos = findViewById(R.id.photosRecycler)

        mRecyclerPhotos.layoutManager =
                GridLayoutManager(getContext(), getContext().resources.getInteger(R.integer.photos_recycler_span_count))
        mRecyclerPhotos.setHasFixedSize(true)
        mAdapterPhotos = PhotosRecyclerAdapter(this, mViewMvcFactory)
        mRecyclerPhotos.adapter = mAdapterPhotos

        mSwipeRefreshLayout = findViewById(R.id.photosPullToRefresh)
        mSwipeRefreshLayout.setOnRefreshListener(this)

        mToolbar = findViewById(R.id.toolbar)
        mToolbarViewMvc = mViewMvcFactory.getToolbarViewMvc(mToolbar)
        initToolbar()
    }

    private fun initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView())
        mToolbarViewMvc.setTitle(getContext().getString(R.string.toolbar_title_photos))
    }

    override fun bindPhotos(photos: List<Photo>) {
        mAdapterPhotos.bindPhotos(photos)
    }

    override fun showProgressIndication() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgressIndication() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun onPhotoClicked(photo: Photo) {
        for (listener in getListeners()) {
            listener.onPhotoClicked(photo)
        }
    }

    override fun onRefresh() {
        for (listener in getListeners()) {
            listener.onPullToRefresh()
        }
    }

}