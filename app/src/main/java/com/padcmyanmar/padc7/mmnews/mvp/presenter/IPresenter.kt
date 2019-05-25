package com.padcmyanmar.padc7.mmnews.mvp.presenter

import android.content.Context

interface IPresenter {

    fun onUiReady()
    fun onRefresh()
    fun onListEndReach()
    fun onTapFavNews()
    fun onTapJustForYouNews()
    fun onTapLatestNews()
    fun onTapNews(id : String)
}