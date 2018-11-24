package com.pihrit.photos.screens.common.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import com.pihrit.photos.R
import com.pihrit.photos.screens.common.controllers.BackPressDispatcher
import com.pihrit.photos.screens.common.controllers.BackPressListener
import com.pihrit.photos.screens.common.controllers.BaseActivity
import com.pihrit.photos.screens.common.fragmentgramehelper.FragmentFrameWrapper
import com.pihrit.photos.screens.common.screensnavigator.ScreensNavigator
import javax.inject.Inject

class MainActivity : BaseActivity(), FragmentFrameWrapper, BackPressDispatcher {

    @Inject
    lateinit var mScreensNavigator: ScreensNavigator
    private val mBackPressListeners = HashSet<BackPressListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)

        setContentView(R.layout.layout_content_frame)

        if (savedInstanceState == null) {
            mScreensNavigator.toPhotosList()
        }
    }

    @SuppressLint("WrongViewCast")
    override fun getFragmentFrame(): FrameLayout {
        return findViewById(R.id.frame_content)
    }

    override fun registerListener(listener: BackPressListener) {
        mBackPressListeners.add(listener)
    }

    override fun unregisterListener(listener: BackPressListener) {
        mBackPressListeners.remove(listener)
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAnyListener = false
        for (listener in mBackPressListeners) {
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed()
        }
    }

}
