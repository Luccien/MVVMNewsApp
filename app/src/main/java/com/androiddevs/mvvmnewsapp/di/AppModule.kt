package com.androiddevs.mvvmnewsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("tex")
    fun provideText():String = "thghdw"

    @Provides
    @Singleton
    @Named("tex2")
    fun provideText2():String = "thghdwoooooooooooooooooo"

    @Provides
    @Singleton
    @Named("tex3")
    fun provideText3():String = "333333333333333oo"

}