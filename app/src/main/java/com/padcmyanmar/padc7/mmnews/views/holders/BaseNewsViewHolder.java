package com.padcmyanmar.padc7.mmnews.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.mvp.presenter.NewsListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseNewsViewHolder extends BaseViewHolder<NewsVO> {


    TextView tvBriefNews;

    ImageView ivNewsHeroImage;

    NewsListPresenter presenter1;

    NewsVO newsVO;

    public BaseNewsViewHolder(@NonNull View itemView, final NewsListPresenter presenter) {
        super(itemView);
        this.presenter1 = presenter;
        tvBriefNews = itemView.findViewById(R.id.tv_brief_news);
        ivNewsHeroImage = itemView.findViewById(R.id.iv_news_hero_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter1.onTapNews(newsVO.getNewsId());
            }
        });
    }

    @Override
    public void bindData(NewsVO data) {
        this.newsVO = data;
        tvBriefNews.setText(data.getBrief());

        if(ivNewsHeroImage != null) {
            Glide.with(itemView)
                    .load(data.getHeroImage())
                    .into(ivNewsHeroImage);
        }
    }
}
