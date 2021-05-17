package com.androiddevs.mvvmnewsapp.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Event
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response


@ExperimentalCoroutinesApi
class NewsViewModel
    @ViewModelInject constructor(
    private val defaultNewsRepository: NewsRepository
): ViewModel() {


    val insertBreakingNewsStatus: MutableLiveData<Event<Resource<Article>>> = MutableLiveData()

    val breakingNews: MutableLiveData<Event<Resource<NewsResponse>>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Event<Resource<NewsResponse>>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null
    var newSearchQuery:String? = null
    var oldSearchQuery:String? = null


    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        safeBreakingNewsCall(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        safeSearchNewsCall(searchQuery)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.error(response.message(),null)
    }




    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                } else {
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.error(response.message(),null)
    }




    fun saveArticleIntoDB(article: Article) = viewModelScope.launch {
        defaultNewsRepository.upsert(article)
    }

    // TODO exchange upsert (set and update separation)-->  best practice
    fun saveArticle(article: Article) {

        if(article.urlToImage !="") {
            saveArticleIntoDB(article)
            insertBreakingNewsStatus.postValue(Event(Resource.success(article)))
        }
        else{
            insertBreakingNewsStatus.postValue(Event(Resource.error("Error: The urlToImage is missing",null)))
            return
        }
    }

    fun getSavedNews() = defaultNewsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        defaultNewsRepository.deleteArticle(article)
    }



    private suspend fun safeSearchNewsCall(searchQuery: String) {
        newSearchQuery = searchQuery
        searchNews.postValue(Event(Resource.loading(null)))
        try {
            if(hasInternetConnection()) {
                val response = defaultNewsRepository.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(Event(handleSearchNewsResponse(response)))
            } else {
                searchNews.postValue(Event(Resource.error("No internet connection",null)))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(Event(Resource.error("Network Failure",null)))
                else -> searchNews.postValue(Event(Resource.error("Conversion Error",null)))
            }
        }
    }

    private suspend fun safeBreakingNewsCall(countryCode: String) {
        breakingNews.postValue(Event(Resource.loading(null)))
        try {
            if(hasInternetConnection()) {
                val response = defaultNewsRepository.getBreakingNews(countryCode, breakingNewsPage)
                breakingNews.postValue(Event(handleBreakingNewsResponse(response)))
            } else {
                breakingNews.postValue(Event(Resource.error("No internet connection",null)))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> breakingNews.postValue(Event(Resource.error("Network Failure",null)))
                else -> breakingNews.postValue(Event(Resource.error("Conversion Error",null)))
            }
        }
    }

    // TODO IMPLEMENT
    private fun hasInternetConnection(): Boolean {
       /* val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false

        */
        return true
    }

}












