package com.padcmyanmar.padc7.mmnews.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO

interface INewsModel {


    fun addCommentNews(news: NewsVO, comment: CommentVO)

    fun favoriteNews(news: NewsVO, favorite: FavoriteVO)

    fun sendNewsTo(news: NewsVO, sendTo: SendToVO)

    fun getNewsById(id : String) : LiveData<NewsVO>


    fun getNews(isForce: Boolean,mContext : Context,error:(message : String) -> Unit , status:(Int) -> Unit): LiveData<List<NewsVO>>

    fun loadMoreNews(mContext: Context,error:(message: String) -> Unit,status:(Int) -> Unit) : LiveData<List<NewsVO>>

    interface NewsDelegate {

        fun onSuccess(newsList: List<NewsVO>)

        fun onError(msg: String)
    }
}