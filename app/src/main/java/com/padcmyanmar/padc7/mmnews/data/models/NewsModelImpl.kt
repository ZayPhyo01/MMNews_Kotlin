package com.padcmyanmar.padc7.mmnews.data.models

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase

object NewsModelImpl : BaseModel(), INewsModel {

    override fun getNewsById(id: String): LiveData<NewsVO> {
        return mNewsDB.newsDao().getNewsById(id)
    }

    private val DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916"
    private val KEY_NEWS_PAGE = "KEY_NEWS_PAGE"

    fun initModel(context: Context) {
        mDataAgent = RetrofitDA.instance
        mNewsDB = NewsDatabase.getDatabase(context)
    }


    fun getInstance(): NewsModelImpl {
        return NewsModelImpl

    }

    override fun addCommentNews(news: NewsVO, comment: CommentVO) {

    }

    override fun favoriteNews(news: NewsVO, favorite: FavoriteVO) {

    }

    override fun sendNewsTo(news: NewsVO, sendTo: SendToVO) {

    }

    override fun getNews(isForce: Boolean, mContext: Context, error: (message: String) -> Unit, status: (Int) -> Unit): LiveData<List<NewsVO>> {
        var newsPage = getNewsPage(mContext)
        if (isForce) {
            newsPage = 1
        }

        mDataAgent.loadNews(newsPage, DUMMY_ACCESS_TOKEN,
                {
                    if (!isForce) {
                        setNewsPage(getNewsPage(mContext) + 1, mContext)
                    }

                    val insertedNewsCount = mNewsDB.newsDao().saveNews(it, mNewsDB.commentDao())
                    Log.d("id :  ", " $insertedNewsCount")
                }, {
            error.invoke(it)
        },
                {
                }
        )




        return mNewsDB.newsDao().loadNews()
    }

    override fun loadMoreNews(mContext: Context, error: (message: String) -> Unit, status: (Int) -> Unit): LiveData<List<NewsVO>> {
        val count = getNewsPage(mContext)
        Log.d("count page ", "$count ")
        mDataAgent.loadNews(count, DUMMY_ACCESS_TOKEN,
                {
                    setNewsPage(getNewsPage(mContext) + 1, mContext)
                    val count = mNewsDB.newsDao().saveNews(it, mNewsDB.commentDao())

                }, {
            error.invoke(it)
        }, {
            status.invoke(it)
        }
        )

        return mNewsDB.newsDao().loadNews()
    }

    private fun getNewsPage(mContext: Context): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return sharedPreferences.getInt(KEY_NEWS_PAGE, 1)
    }

    private fun setNewsPage(newPageNumber: Int, mContext: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        sharedPreferences.edit().putInt(KEY_NEWS_PAGE, newPageNumber).apply()
    }
}