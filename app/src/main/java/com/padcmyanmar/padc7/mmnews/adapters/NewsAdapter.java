package com.padcmyanmar.padc7.mmnews.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import com.padcmyanmar.padc7.mmnews.mvp.presenter.NewsListPresenter;
import com.padcmyanmar.padc7.mmnews.views.holders.BaseNewsViewHolder;
import com.padcmyanmar.padc7.mmnews.views.holders.NewsSmallViewHolder;
import com.padcmyanmar.padc7.mmnews.views.holders.NewsViewHolder;

public class NewsAdapter extends BaseRecyclerAdapter<BaseNewsViewHolder, NewsVO> {

    private static final int REGULAR_NEWS_VIEW = 1234;
    private static final int SMALL_NEWS_VIEW = 2345;

    private NewsListPresenter listpresenter;

    public NewsAdapter(NewsListPresenter presenter) {
        listpresenter = presenter;
    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case REGULAR_NEWS_VIEW:
                View itemView = layoutInflater.inflate(R.layout.view_item_news, viewGroup, false);
                return new NewsViewHolder(itemView, listpresenter);
            case SMALL_NEWS_VIEW:
                View itemViewSmall = layoutInflater.inflate(R.layout.view_item_news_small, viewGroup, false);
                return new NewsSmallViewHolder(itemViewSmall,listpresenter);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 1 == 0) {
            return REGULAR_NEWS_VIEW;
        } else {
            return SMALL_NEWS_VIEW;
        }
    }
}
