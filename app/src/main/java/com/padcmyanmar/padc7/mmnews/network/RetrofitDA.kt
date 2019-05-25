package com.padcmyanmar.padc7.mmnews.network


import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate
import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse
import com.padcmyanmar.padc7.mmnews.network.responses.LoginResponse

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDA private constructor() : NewsDataAgent {

    private val mNewsAPI: NewsAPI

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        mNewsAPI = retrofit.create(NewsAPI::class.java)
    }

    override fun loadNews(page: Int, accessToken: String, result:(List<NewsVO>) -> Unit, error:(String) -> Unit, statusError:(Int) -> Unit) {

        val callNewsResponse = mNewsAPI.loadNews(accessToken, page)
        callNewsResponse.enqueue(object : NewsCallback<GetNewsResponse>(
                {
                    result.invoke(it.newsList)
                },
                {
                    error.invoke(it)
                },
                {
                    statusError.invoke(it)
                }

        ) {

        })

        /*
        callNewsResponse.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null) {
                    if (newsResponse.isResponseSuccess()) {
                        newsResponseDelegate.onSuccess(newsResponse.getNewsList());
                    } else {
                        newsResponseDelegate.onFail(newsResponse.getMessage());
                    }
                } else {
                    newsResponseDelegate.onFail("Response is null.");
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                newsResponseDelegate.onFail(t.getMessage());
            }
        });
        */
    }

    override fun login(phoneNumber: String, password: String,result:(LoginUserVO) -> Unit,error:(String) -> Unit , statusError:(Int) -> Unit) {
        val callLoginResponse = mNewsAPI.login(phoneNumber, password)
        callLoginResponse.enqueue(object : NewsCallback<LoginResponse>({
        result.invoke(it.loginUser)
        },{
            error.invoke(it)
        },{
            statusError.invoke(it)
        })


        {
           /* override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                super.onResponse(call, response)
                val loginResponse = response.body()
                val loginUser = loginResponse!!.loginUser
                networkDelegate.onSuccess(loginUser)*/
            }
        )
    }

    override fun register(phoneNUmber: String, name: String, password: String) {

    }

    companion object {

        private var objInstance: RetrofitDA? = null

        val instance: RetrofitDA
            get() {
                if (objInstance == null) {
                    objInstance = RetrofitDA()
                }
                return objInstance as RetrofitDA
            }
    }
}
