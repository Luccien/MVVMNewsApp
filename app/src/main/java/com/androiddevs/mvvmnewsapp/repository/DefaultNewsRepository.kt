package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.db.ArticleDao
import com.androiddevs.mvvmnewsapp.models.Article
import javax.inject.Inject
import javax.inject.Named

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
