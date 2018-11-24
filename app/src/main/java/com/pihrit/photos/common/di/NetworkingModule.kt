package com.pihrit.photos.common.di

import com.pihrit.photos.networking.JSONPlaceholderAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JSONPlaceholderAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getJSONPlaceholderAPI(retrofit: Retrofit): JSONPlaceholderAPI {
        return retrofit.create(JSONPlaceholderAPI::class.java)
    }

}
