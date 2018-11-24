package com.pihrit.photos.common.di.application

import com.pihrit.photos.common.di.NetworkingModule
import com.pihrit.photos.common.di.presentation.PresentationComponent
import com.pihrit.photos.common.di.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkingModule::class])
interface ApplicationComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}