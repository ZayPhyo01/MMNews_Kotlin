package com.padcmyanmar.padc7.mmnews.mvp.presenter

import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate
import com.padcmyanmar.padc7.mmnews.mvp.view.LoginView

class LoginPresenter (val loginView: LoginView): BasePresenter(),ILoginPresenter{


    val userModelImpl : UserModelImpl

    init {
        userModelImpl = UserModelImpl.getInstance() as UserModelImpl
    }
    override fun onTaplogin() {
         userModelImpl.login(" d"," d",object :LoginDelegate{
             override fun onSuccess(loginUser: LoginUserVO?) {
                  loginView.login(loginUser!!)
             }

             override fun onFail(msg: String?) {
                   }
         })
    }

    override fun onCreate() {

    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}