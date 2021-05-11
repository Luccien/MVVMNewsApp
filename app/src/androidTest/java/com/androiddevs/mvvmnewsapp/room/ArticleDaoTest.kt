package com.androiddevs.mvvmnewsapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.mvvmnewsapp.getOrAwaitValue
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.Source
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@SmallTest
@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database:ArticleDatabase
    private lateinit var dao:ArticleDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ArticleDatabase::class.java//,
        //"article_db.db"
        ).allowMainThreadQueries().build()
        dao = database.getArticleDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insert() = runBlockingTest{
        val article = Article(1, "authooor", "conten", "ds", "fd", Source("myName","myName"), "f", "r", "r")
        dao.upsert(article)
        val allArticles = dao.getAllArticles().getOrAwaitValue()
        assertThat(allArticles).contains(article)
    }


}