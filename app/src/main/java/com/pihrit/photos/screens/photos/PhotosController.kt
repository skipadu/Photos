package com.pihrit.photos.screens.photos

import com.pihrit.photos.photos.FetchPhotosUseCase
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.ToastHelper
import com.pihrit.photos.screens.common.controllers.BackPressDispatcher
import com.pihrit.photos.screens.common.controllers.BackPressListener
import com.pihrit.photos.screens.common.screensnavigator.ScreensNavigator

class PhotosController(
    private val mFetchPhotosUseCase: FetchPhotosUseCase,
    private val mScreensNavigator: ScreensNavigator,
    private val mBackPressedDispatcher: BackPressDispatcher,
    private val mToastHelper: ToastHelper
) : PhotosViewMvc.Listener, FetchPhotosUseCase.Listener, BackPressListener {

    private lateinit var mViewMvc: PhotosViewMvc


    private var mIsDataFetched = false
    private lateinit var mPhotos: ArrayList<Photo>

    fun bindView(viewMvc: PhotosViewMvc) {
        mViewMvc = viewMvc
    }

    fun onStart() {
        mViewMvc.registerListener(this)
        mFetchPhotosUseCase.registerListener(this)
        mBackPressedDispatcher.registerListener(this)

        mViewMvc.showProgressIndication()

        if (mIsDataFetched) {
            updatePhotos()
        } else {
            mFetchPhotosUseCase.fetchPhotosAndNotify()
        }
    }

    fun onStop() {
        mViewMvc.unregisterListener(this)
        mFetchPhotosUseCase.unregisterListener(this)
        mBackPressedDispatcher.unregisterListener(this)
    }

    override fun onPhotoClicked(photo: Photo) {
        mScreensNavigator.toPhotoDetails(photo)
    }

    override fun onPullToRefresh() {
        mFetchPhotosUseCase.fetchPhotosAndNotify()
    }

    override fun onPhotosFetched(photos: ArrayList<Photo>) {
        mIsDataFetched = true
        mPhotos = photos
        mToastHelper.showPhotosRefreshed()
        updatePhotos()
    }

    private fun updatePhotos() {
        mViewMvc.hideProgressIndication()
        mViewMvc.bindPhotos(mPhotos)
    }

    override fun onPhotosFetchFailed() {
        mViewMvc.hideProgressIndication()
        mToastHelper.showPhotosFetchError()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}