package com.pihrit.photos.screens.common.screensnavigator

import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.fragmentgramehelper.FragmentFrameHelper
import com.pihrit.photos.screens.photodetails.PhotoDetailsFragment
import com.pihrit.photos.screens.photos.PhotosFragment

class ScreensNavigator(private val mFragmentFrameHelper: FragmentFrameHelper) {

    fun toPhotosList() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(PhotosFragment.newInstance())
    }

    fun toPhotoDetails(photo: Photo) {
        mFragmentFrameHelper.replaceFragment(PhotoDetailsFragment.newInstance(photo))
    }

    fun navigateUp() {
        mFragmentFrameHelper.navigateUp()
    }
}