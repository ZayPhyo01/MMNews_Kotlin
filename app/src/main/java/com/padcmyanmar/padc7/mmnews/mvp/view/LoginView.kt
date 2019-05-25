package com.padcmyanmar.padc7.mmnews.mvp.view

import com.padcmyanmar.padc7.mmnews.data.vos.ActedUserVO
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO

interface LoginView {

    fun login(userVO: LoginUserVO)

}