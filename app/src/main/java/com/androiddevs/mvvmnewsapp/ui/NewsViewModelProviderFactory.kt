package com.androiddevs.mvvmnewsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.repository.DefaultNewsRepository

class NewsViewModelProviderFactory(
    val app: Application,
    val defaultNewsRepository: DefaultNewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, defaultNewsRepository) as T
    }
}