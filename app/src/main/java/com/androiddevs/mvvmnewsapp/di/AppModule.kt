package com.androiddevs.mvvmnewsapp.di

import android.content.Context
import androidx.room.Room
import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.db.ArticleDao
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.repository.DefaultNewsRepository
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Constants
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context.applicationContext,
        ArticleDatabase::class.java,
        "article_db.db"
    ).build()


    @Singleton
    @Provides
    fun provideArticleDao(
        database: ArticleDatabase
    ) = database.getArticleDao()


        @Singleton
        @Provides
        fun provideDefaultNewsRepository(
            dao: ArticleDao,
            newsApi:NewsAPI
        ) = DefaultNewsRepository(dao, newsApi) as NewsRepository


    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
             .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
             .build()
            .create(NewsAPI::class.java)
    }


}