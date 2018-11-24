package com.pihrit.photos.common

import android.app.Application
import com.pihrit.photos.common.di.application.ApplicationComponent
import com.pihrit.photos.common.di.application.DaggerApplicationComponent

class PhotosApplication : Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return mApplicationComponent
    }

}