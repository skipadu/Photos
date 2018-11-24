package com.pihrit.photos.screens.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pihrit.photos.screens.common.controllers.BaseFragment
import com.pihrit.photos.screens.common.views.ViewMvcFactory
import javax.inject.Inject

class PhotosFragment : BaseFragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var mPhotosController: PhotosController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mViewMvc = mViewMvcFactory.newInstance(PhotosViewMvc::class.java, container)
        mPhotosController.bindView(mViewMvc)
        return mViewMvc.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mPhotosController.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPhotosController.onStop()
    }

}
