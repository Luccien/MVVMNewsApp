package com.androiddevs.mvvmnewsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.mvvmnewsapp.MainCoroutineRule
import com.androiddevs.mvvmnewsapp.getOrAwaitValueTest
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.Source
import com.androiddevs.mvvmnewsapp.repository.FakeDefaultNewsRepository
import com.androiddevs.mvvmnewsapp.util.Status
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        viewModel = NewsViewModel(FakeDefaultNewsRepository())
    }

    @Test
    fun `saveArticle - article urlToImage="" returns error`(){
        viewModel.saveArticle(Article(1,"a","a","a","a", Source(1,"a"),"a","a",""))

        val value = viewModel.insertBreakingNewsStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }



}