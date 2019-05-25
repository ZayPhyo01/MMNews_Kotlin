package com.padcmyanmar.padc7.mmnews.mvp.view

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO

interface NewsListView : BaseView{

    fun showNewsList(newsList : List<NewsVO>)
    fun showMoreNewsList(newsList: List<NewsVO>)
    fun nevigateTo(id:String)
    fun nevigateToJustForYouNews()
    fun nevigateToFavNews()
    fun nevigateToLatestNews()
    fun nevigateToLogin()


}