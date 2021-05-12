package com.androiddevs.mvvmnewsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import retrofit2.Response

class FakeDefaultNewsRepository :NewsRepository{


    private val articles = mutableListOf<Article>()

    private val observableArticles = MutableLiveData<List<Article>>(articles)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableArticles.postValue(articles)
    }



    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> {
            return Response.success(NewsResponse(articles,"200",1))//observableArticles,"200","head","raw")
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
                    return Response.success(NewsResponse(articles,"200",1))//observableArticles,"200","head","raw")

    }

    override suspend fun upsert(article: Article): Long {
         articles.add(article)
        refreshLiveData()
        return 1
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return observableArticles
    }

    override suspend fun deleteArticle(article: Article) {
        articles.remove(article)
             refreshLiveData()
    }


    /*
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        newsApi.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        newsApi.searchForNews(searchQuery, pageNumber)


    override suspend fun upsert(article: Article)   = dao.upsert(article)

    override fun getSavedNews() = dao.getAllArticles()

    override suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)
*/


}