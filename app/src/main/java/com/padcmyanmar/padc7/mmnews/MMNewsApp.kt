package com.padcmyanmar.padc7.mmnews

import android.app.Application
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl

class MMNewsApp : Application(){

    override fun onCreate() {
        super.onCreate()

        UserModelImpl.initUserModel(applicationContext)
        NewsModelImpl.initModel(applicationContext)
    }
}