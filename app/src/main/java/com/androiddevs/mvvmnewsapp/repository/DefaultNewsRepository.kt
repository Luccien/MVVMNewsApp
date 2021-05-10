package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.retrofit.NewsAPI
import com.androiddevs.mvvmnewsapp.room.ArticleDao
import com.androiddevs.mvvmnewsapp.models.Article

class DefaultNewsRepository constructor(
    private val dao: ArticleDao,
    private val newsApi:NewsAPI
):NewsRepository {


    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        newsApi.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        newsApi.searchForNews(searchQuery, pageNumber)


    override suspend fun upsert(article: Article)   = dao.upsert(article)

    override fun getSavedNews() = dao.getAllArticles()

    override suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)



}
