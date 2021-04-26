package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.models.Article

class DefaultNewsRepository(
    val db: ArticleDatabase
):NewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    override suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    override fun getSavedNews() = db.getArticleDao().getAllArticles()

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}