package com.pihrit.photos.screens.common.controllers

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.pihrit.photos.common.PhotosApplication
import com.pihrit.photos.common.di.application.ApplicationComponent
import com.pihrit.photos.common.di.presentation.PresentationComponent
import com.pihrit.photos.common.di.presentation.PresentationModule


open class BaseActivity : AppCompatActivity() {

    private var mIsInjectorUsed = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("There is no need to use injector more than once")
        }
        mIsInjectorUsed = true

        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as PhotosApplication).getApplicationComponent()
    }
}