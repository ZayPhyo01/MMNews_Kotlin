package com.padcmyanmar.padc7.mmnews.views.holders;


import android.view.View;


import androidx.annotation.NonNull;

import com.padcmyanmar.padc7.mmnews.mvp.presenter.NewsListPresenter;

public class NewsViewHolder extends BaseNewsViewHolder {

    private NewsListPresenter mDelegate;

    public NewsViewHolder(@NonNull View itemView, @NonNull NewsListPresenter newsPresenter) {
        super(itemView,newsPresenter);

    }
}
