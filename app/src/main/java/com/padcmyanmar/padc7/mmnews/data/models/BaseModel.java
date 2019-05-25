package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;

import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA;
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase;

public abstract class BaseModel {

    protected NewsDataAgent mDataAgent;
    protected NewsDatabase mNewsDB;


    public BaseModel() {

    }

}
