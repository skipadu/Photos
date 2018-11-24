package com.pihrit.photos.common.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.pihrit.photos.photos.FetchPhotosUseCase
import com.pihrit.photos.screens.common.ToastHelper
import com.pihrit.photos.screens.common.controllers.BackPressDispatcher
import com.pihrit.photos.screens.common.fragmentgramehelper.FragmentFrameHelper
import com.pihrit.photos.screens.common.fragmentgramehelper.FragmentFrameWrapper
import com.pihrit.photos.screens.common.screensnavigator.ScreensNavigator
import com.pihrit.photos.screens.photos.PhotosController
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val mActivity: FragmentActivity) {

    @Provides
    fun getLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(mActivity)
    }

    @Provides
    fun getActivity(): FragmentActivity {
        return mActivity
    }

    @Provides
    fun context(activity: Activity): Context {
        return activity
    }

    @Provides
    fun getScreensNavigator(fragmentFrameHelper: FragmentFrameHelper): ScreensNavigator {
        return ScreensNavigator(fragmentFrameHelper)
    }

    @Provides
    fun getBackPressDispatcher(activity: FragmentActivity): BackPressDispatcher {
        return activity as BackPressDispatcher
    }

    @Provides
    fun getFragmentFrameHelper(
        activity: FragmentActivity,
        fragmentFrameWrapper: FragmentFrameWrapper,
        fragmentManager: FragmentManager
    ): FragmentFrameHelper {
        return FragmentFrameHelper(activity, fragmentFrameWrapper, fragmentManager)
    }

    @Provides
    fun getFragmentManager(activity: FragmentActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun getFragmentFrameWrapper(activity: FragmentActivity): FragmentFrameWrapper {
        return activity as FragmentFrameWrapper
    }

    @Provides
    fun getPhotosController(
        fetchPhotosUseCase: FetchPhotosUseCase,
        screensNavigator: ScreensNavigator, backPressDispatcher: BackPressDispatcher, toastHelper: ToastHelper
    ): PhotosController {
        return PhotosController(fetchPhotosUseCase, screensNavigator, backPressDispatcher, toastHelper)
    }

    @Provides
    fun getToastHelper(activity: FragmentActivity): ToastHelper {
        return ToastHelper(activity)
    }

}
