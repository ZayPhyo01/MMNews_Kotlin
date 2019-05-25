package com.padcmyanmar.padc7.mmnews.network



import com.padcmyanmar.padc7.mmnews.delegates.BaseNetworkDelegate
import com.padcmyanmar.padc7.mmnews.network.responses.BaseResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NewsCallback<T : BaseResponse>  constructor(val responsefun:(T)->Unit,val fail:(String)->Unit,val status:(Int) -> Unit) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val newsResponse = response.body()
        if (newsResponse == null) {
fail.invoke("Empty data")
            return
        } else {
            if (!newsResponse.isResponseSuccess) {
                status.invoke(newsResponse.code)
                return
            }
            else{
                responsefun.invoke(newsResponse)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        fail.invoke("Something wrong")
    }
}
