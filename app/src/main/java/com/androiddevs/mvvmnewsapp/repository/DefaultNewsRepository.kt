package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.models.Article
import javax.inject.Inject
import javax.inject.Named

class DefaultNewsRepository constructor(
    //val db: ArticleDatabase
    private val newsApi:NewsAPI
):NewsRepository {

    @Inject
    @Named("tex3")
    lateinit var txtiii333:String

   //@Inject
    //lateinit var newsApi:NewsAPI



    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        newsApi.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        newsApi.searchForNews(searchQuery, pageNumber)



/*  ORGINAL !!!
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

*/
    /////////////
    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)


     */

    suspend fun upsert(article: Article)   = 3L//db.getArticleDao().upsert(article)
    //{return Unit}//

    fun getSavedNews() = 4//MutableLiveData()//null//LiveData(List(Article(0,"e","e","e","e",null,"r",null,null))) //db.getArticleDao().getAllArticles()

        suspend fun deleteArticle(article: Article) = 6

/*

    ////////////  TESTING WITHOUT DB
    override suspend fun upsert(article: Article)   = 3L//db.getArticleDao().upsert(article)
    //{return Unit}//

    override fun getSavedNews() = //MutableLiveData()//null//LiveData(List(Article(0,"e","e","e","e",null,"r",null,null))) //db.getArticleDao().getAllArticles()

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
*/
}
