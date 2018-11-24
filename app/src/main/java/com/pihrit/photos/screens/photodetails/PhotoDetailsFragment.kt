package com.pihrit.photos.screens.photodetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pihrit.photos.photos.Photo
import com.pihrit.photos.screens.common.controllers.BackPressDispatcher
import com.pihrit.photos.screens.common.controllers.BackPressListener
import com.pihrit.photos.screens.common.controllers.BaseFragment
import com.pihrit.photos.screens.common.screensnavigator.ScreensNavigator
import com.pihrit.photos.screens.common.views.ViewMvcFactory
import javax.inject.Inject

class PhotoDetailsFragment : BaseFragment(), PhotoDetailsViewMvc.Listener, BackPressListener {

    companion object {
        private const val PHOTO = "photo"

        fun newInstance(photo: Photo?): PhotoDetailsFragment {
            val args = Bundle()
            args.putSerializable(PHOTO, photo)

            val fragment = PhotoDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mBackPressDispatcher: BackPressDispatcher
    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var mScreensNavigator: ScreensNavigator

    private lateinit var mViewMvc: PhotoDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewMvc = mViewMvcFactory.newInstance(PhotoDetailsViewMvc::class.java, null)
        mViewMvc.bindPhoto(getPhoto())

        return mViewMvc.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mBackPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        mBackPressDispatcher.unregisterListener(this)
    }

    private fun getPhoto(): Photo {
        return arguments?.getSerializable(PHOTO) as Photo
    }

    override fun onNavigateUpClicked() {
        mScreensNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}
