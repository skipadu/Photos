package com.pihrit.photos.common.di.presentation

import com.pihrit.photos.screens.common.main.MainActivity
import com.pihrit.photos.screens.photodetails.PhotoDetailsFragment
import com.pihrit.photos.screens.photos.PhotosFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(photoDetailsFragment: PhotoDetailsFragment)
    fun inject(photosFragment: PhotosFragment)
}