package com.padcmyanmar.padc7.mmnews.mvp.presenter

import android.content.Context
import android.util.Log
import com.padcmyanmar.padc7.mmnews.data.models.*

import com.padcmyanmar.padc7.mmnews.mvp.view.NewsListView

class NewsListPresenter(val newsListView: NewsListView) : BasePresenter(), IPresenter {



    val userModelImpl: UserModel


    override fun onTapFavNews() {
        newsListView.nevigateToFavNews()
    }

    override fun onTapJustForYouNews() {
        newsListView.nevigateToJustForYouNews()
    }

    override fun onTapLatestNews() {
        newsListView.nevigateToLatestNews()
    }

    override fun onTapNews(id : String) {
        Log.d("Tap : "," "+id)
        newsListView.nevigateTo(id)
    }


    override fun onUiReady() {
        newsModelImpl.getNews(true ,newsListView.getContext(),{
            Log.d("Error ",it)

        },{
            Log.d("Status "," $it")
        }).observeForever { t ->
            newsListView.showNewsList(t)
        }
    }

    override fun onRefresh() {
    }

    override fun onListEndReach() {
        newsModelImpl.loadMoreNews(newsListView.getContext(),{

        },{

        }).observeForever { t ->
            if (t != null)
                newsListView.showMoreNewsList(t)
        }

    }

    val newsModelImpl: NewsModelImpl

    init {
        newsModelImpl = NewsModelImpl.getInstance()
        userModelImpl = UserModelImpl.getInstance()
    }


    override fun onCreate() {


    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }


}