package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.models.Article
import javax.inject.Inject
import javax.inject.Named

class DefaultNewsRepository(
    val db: ArticleDatabase
):NewsRepository {

    @Inject
    @Named("tex3")
    lateinit var txtiii333:String

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    override suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    override fun getSavedNews() = db.getArticleDao().getAllArticles()

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}