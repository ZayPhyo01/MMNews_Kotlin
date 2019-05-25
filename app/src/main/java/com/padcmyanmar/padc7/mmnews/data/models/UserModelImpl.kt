package com.padcmyanmar.padc7.mmnews.data.models

import android.content.Context
import android.util.Log
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase

object UserModelImpl : BaseModel() , UserModel {

    fun initUserModel(context: Context) {
        NewsModelImpl.mDataAgent = RetrofitDA.instance
        NewsModelImpl.mNewsDB = NewsDatabase.getDatabase(context)

    }

    fun getInstance(): UserModel {
        return UserModelImpl
    }

    override fun login(emailOrPassword: String, password: String, loginDelegate: LoginDelegate) {
    /*    mDataAgent.login(emailOrPassword, password, object : LoginDelegate {
            override fun onSuccess(loginUser: LoginUserVO) {
                val userId = mNewsDB.loginUserDao().inserLoginUser(loginUser)
                Log.d("", "userId : $userId")
                loginDelegate.onSuccess(loginUser)
            }

            override fun onFail(msg: String) {
                loginDelegate.onFail(msg)
            }
        })*/
    }

    override fun getLoginUser(): LoginUserVO {
        return mNewsDB.loginUserDao().loginUser
    }

    override fun isUserLogin(): Boolean {
        return mNewsDB.loginUserDao().loginUser != null
    }

    override fun onUserLogout() {
        mNewsDB.loginUserDao().deleteLoginUser()
    }
}