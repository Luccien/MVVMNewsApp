package com.androiddevs.mvvmnewsapp.di

import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.util.Constants
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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




/*
    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

 */
/////////////////////////////
    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI {
//
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
  ////
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()
        .create(NewsAPI::class.java)
    }
/////////////////////////////////////

/*
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy {
        retrofit.create(NewsAPI::class.java)
    }
*/

}