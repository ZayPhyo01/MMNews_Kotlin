package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.padcmyanmar.padc7.mmnews.data.models.INewsModel;
import com.padcmyanmar.padc7.mmnews.data.models.NewsModel;
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl;
import com.padcmyanmar.padc7.mmnews.data.models.UserModel;
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl;
import com.padcmyanmar.padc7.mmnews.mvp.view.BaseView;

import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected INewsModel mNewsModel;
    protected UserModel mUserModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsModel = NewsModelImpl.INSTANCE.getInstance();
        mUserModel = UserModelImpl.INSTANCE.getInstance();
    }

    @NotNull
    @Override
    public Context getContext() {
        return getApplicationContext();
    }


}
