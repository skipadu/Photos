package com.pihrit.photos.screens.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.pihrit.photos.R
import com.pihrit.photos.screens.common.views.BaseViewMvc

class ToolbarViewMvc(mInflater: LayoutInflater, mParent: ViewGroup?) : BaseViewMvc() {

    interface NavigateUpClickListener {
        fun onNavigateUpClicked()
    }

    private val mTitleTextView: TextView
    private val mBackButton: ImageButton

    private lateinit var mNavigateUpClickListener: NavigateUpClickListener

    init {
        setRootView(mInflater.inflate(R.layout.layout_toolbar, mParent, false))

        mTitleTextView = findViewById(R.id.tvToolbarTitle)
        mBackButton = findViewById(R.id.btnBack)
        mBackButton.setOnClickListener {
            mNavigateUpClickListener.onNavigateUpClicked()
        }
    }

    fun setTitle(title: String) {
        mTitleTextView.text = title
    }

    fun enableUpButtonAndListen(navigateUpClickListener: NavigateUpClickListener) {
        mNavigateUpClickListener = navigateUpClickListener
        mBackButton.visibility = View.VISIBLE
    }
}