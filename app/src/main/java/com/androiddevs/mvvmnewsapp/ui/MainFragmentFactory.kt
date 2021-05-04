package com.androiddevs.mvvmnewsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.androiddevs.mvvmnewsapp.ui.fragments.BreakingNewsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory @Inject constructor(): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            BreakingNewsFragment::class.java.name -> {
                val fragment = BreakingNewsFragment()
                fragment
            }
            /*
            SecondFragment::class.java.name -> {
                val fragment = SecondFragment(someString2)
                fragment
            }*/
            else -> super.instantiate(classLoader, className)
        }
    }


}