package com.pihrit.photos.screens.common.controllers

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.pihrit.photos.common.PhotosApplication
import com.pihrit.photos.common.di.application.ApplicationComponent
import com.pihrit.photos.common.di.presentation.PresentationComponent
import com.pihrit.photos.common.di.presentation.PresentationModule

abstract class BaseFragment : Fragment() {

    private var mIsInjectorUsed = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("There is no need to use injector more than once")
        }
        mIsInjectorUsed = true

        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(activity!!))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (activity?.application as PhotosApplication).getApplicationComponent()
    }
}