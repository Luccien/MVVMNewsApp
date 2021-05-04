package com.androiddevs.mvvmnewsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.androiddevs.mvvmnewsapp.adapters.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.fragments.ArticleFragment
import com.androiddevs.mvvmnewsapp.ui.fragments.BreakingNewsFragment
import com.androiddevs.mvvmnewsapp.ui.fragments.SavedNewsFragment
import com.androiddevs.mvvmnewsapp.ui.fragments.SearchNewsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory @Inject constructor(
    private val newsAdapter: NewsAdapter

): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            BreakingNewsFragment::class.java.name -> {
                val fragment = BreakingNewsFragment(newsAdapter)
                fragment
            }

            SearchNewsFragment::class.java.name -> {
                val fragment = SearchNewsFragment(newsAdapter)
                fragment
            }
            SavedNewsFragment::class.java.name -> {
                val fragment = SavedNewsFragment(newsAdapter)
                fragment
            }
            ArticleFragment::class.java.name -> {
                val fragment = ArticleFragment()
                fragment
            }
            else -> super.instantiate(classLoader, className)
        }
    }


}