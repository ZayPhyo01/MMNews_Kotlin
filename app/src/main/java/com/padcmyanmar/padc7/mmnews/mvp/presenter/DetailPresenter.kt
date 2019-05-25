package com.padcmyanmar.padc7.mmnews.mvp.presenter

import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl
import com.padcmyanmar.padc7.mmnews.mvp.view.DetailView

class DetailPresenter(val detailNews : DetailView) : BasePresenter(),IDetailPresenter {

    val newsModel : NewsModelImpl

    init {
        newsModel = NewsModelImpl.getInstance()
    }

    override fun onUiReady(id : String) {
        newsModel.getNewsById(id).observeForever{
            t -> detailNews.showData(t)
        }

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