package com.padcmyanmar.padc7.mmnews.network

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate

interface NewsDataAgent {

    fun loadNews(page: Int, accessToken: String,result:(List<NewsVO>) -> Unit,error:(String) -> Unit , statusError:(Int) -> Unit)

    fun login(emailOrPhoneNumber: String, password: String,
              result:(LoginUserVO) -> Unit,error:(String) -> Unit , statusError:(Int) -> Unit
              )

    fun register(phoneNUmber: String, name: String, password: String)

    companion object {

        val MMNEWS_BASE_URL = "http://padcmyanmar.com/padc-3/mm-news/apis/"

        val GET_NEWS = "v1/getMMNews.php"

        val PARAM_ACCESS_TOKEN = "access_token"
        val PARAM_PAGE = "page"
    }
}
