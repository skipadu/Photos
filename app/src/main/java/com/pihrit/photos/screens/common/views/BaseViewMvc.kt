package com.pihrit.photos.screens.common.views

import android.content.Context
import android.view.View

abstract class BaseViewMvc : ViewMvc {

    private lateinit var mRootView: View

    override fun getRootView(): View {
        return mRootView
    }

    open fun setRootView(rootView: View) {
        mRootView = rootView
    }

    fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }

    fun getContext(): Context {
        return getRootView().context
    }

}