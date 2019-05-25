package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.adapters.NewsDetailsImagesAdapter;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.mvp.presenter.DetailPresenter;
import com.padcmyanmar.padc7.mmnews.mvp.view.DetailView;
import com.padcmyanmar.padc7.mmnews.utils.AppConstants;

public class NewsDetailsActivity extends BaseActivity implements DetailView {

    DetailPresenter mDetailPresenter;
    String newsId;
    TextView textView;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        textView = findViewById(R.id.tv_news_details);
        newsId = getIntent().getExtras().getString(AppConstants.NEWS_ID);
        Log.d("Detail id ",newsId);
        mDetailPresenter = new DetailPresenter(this);
        mDetailPresenter.onUiReady(newsId);
        }


    @Override
    public void showData(NewsVO newsVO) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView.setText(newsVO.getDetails());


        ViewPager vpNewsDetailsImages = findViewById(R.id.vp_news_details_images);
        NewsDetailsImagesAdapter newsDetailsImagesAdapter = new NewsDetailsImagesAdapter();
        vpNewsDetailsImages.setAdapter(newsDetailsImagesAdapter);

    }
}
