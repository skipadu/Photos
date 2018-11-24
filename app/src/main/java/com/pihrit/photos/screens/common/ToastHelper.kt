package com.pihrit.photos.screens.common

import android.content.Context
import android.widget.Toast
import com.pihrit.photos.R

class ToastHelper(private val context: Context) {

    fun showPhotosRefreshed() {
        Toast.makeText(context, context.getString(R.string.toast_photos_refreshed), Toast.LENGTH_SHORT).show()
    }

    fun showPhotosFetchError() {
        Toast.makeText(context, context.getString(R.string.toast_failed_api_call), Toast.LENGTH_LONG).show()
    }

}