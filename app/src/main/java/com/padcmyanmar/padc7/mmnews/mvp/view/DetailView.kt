package com.padcmyanmar.padc7.mmnews.mvp.view

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO

interface DetailView {

    fun showData(newsVO: NewsVO)
}