# MVVMNewsApp

((A.1)) Task: Implemented Hilt (Injecting Repository, Fragments and ViewModel)
  git commits:   first (a2d4a47549fc9612404f406b9b35f5be8031bcc5) --> cleanup ( 9473a526d3413f5842dc38739224e10372451145 )

Starting Point/Old Version:
In the old version of the NewsAPP there is no Hilt dependency Injection.
Repository and Fragments where initialized in the only one Activity which is NewsActivity.kt.
-->The Activity code should
go in NewsViewModel.kt (inject Repository in NewsViewModel.kt) and inject Fragments via MainFragmentFactory.kt. Inject NewsViewModel.kt into
all the Fragments. The NewsAPI and RoomDB(ArticleDatabase.kt) needs to go to Hilt Module Class(AppModule.kt) to make it injectible.


Files which need to get modiefied/refactored/deleted/added:
- MainNavHostFragment.kt     --> new class   - (boiler) needed for FragmentFactory
- MainFragmentFactory.kt     --> new class    - needed for fragmentFactory

- news_nav_graph.xml               --> stays same
- bottom_navigation_menu.xml        --> stays same

- activity_news.xml                --> modifiy    needs to referende custom MainNavHostFragment.kt instead of default one (video A.3)

- NewsActivity.kt                 --> modify    - refactor/move repository - delete fragment initialization (fragmente will be initialized with MainFragmentFactory) - bottom navigation can stay

- NewsViewModel                   --> modify   - inject repository
- all the Fragments (Article...BreakingNews..)  --> modify   - inject NewsViewModel.kt


-  NewsViewModelProviderFactory.kt --> DELETE  can just go out not used anymore  --
- RetrofitInstance.kt ::::         -->Delete-Refactor  -- moves to Hilt Module Class (MainModule.kt) to make it injectible via hilt

((A.2))
Order of the implementing Steps (have a good order to test on the way)
- document out most of  ViewModel , Activity and Fragment code
- make the repository injectible ->move code to hilt module MainModule.kt (test it)
-  Setup the FragmentFactory  -> inject ViewModel into Fragments (test it) (A.3 video)
- Inject Repository into Fragments


((A.3))
VIDEO:  Fragment Constructor Injection with Hilt and Navigation Component - Coding with Mitch
https://www.youtube.com/watch?v=lH6n4--3R5k

Project Start from:
News App Series - Philipp Lackner
https://www.youtube.com/watch?v=asuOWE5KuFM&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ
---------------

((B1))
Added View Model Test