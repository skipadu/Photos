package com.pihrit.photos.common.di.application

import com.pihrit.photos.networking.JSONPlaceholderAPI
import com.pihrit.photos.photos.FetchPhotosUseCase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun getFetchPhotosUseCase(jsonPlaceholderAPI: JSONPlaceholderAPI): FetchPhotosUseCase {
        return FetchPhotosUseCase(jsonPlaceholderAPI)
    }
}
