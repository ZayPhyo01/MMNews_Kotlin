package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.core.widget.NestedScrollView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.adapters.NewsAdapter;
import com.padcmyanmar.padc7.mmnews.components.SmartRecyclerView;
import com.padcmyanmar.padc7.mmnews.components.SmartScrollListener;
import com.padcmyanmar.padc7.mmnews.data.models.INewsModel;
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import com.padcmyanmar.padc7.mmnews.mvp.presenter.NewsListPresenter;
import com.padcmyanmar.padc7.mmnews.mvp.view.NewsListView;
import com.padcmyanmar.padc7.mmnews.utils.AppConstants;
import com.padcmyanmar.padc7.mmnews.views.pods.EmptyViewPod;
import com.padcmyanmar.padc7.mmnews.views.pods.LoginUserViewPod;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NewsListView {

    private BottomSheetBehavior<NestedScrollView> mBottomSheetBehavior;

    private NewsListPresenter newsListPresenter;


    DrawerLayout mDrawerLayout;

    NestedScrollView nsvBottomSheet;

    NavigationView mNavigationView;


    Toolbar mToolbar;


    SmartRecyclerView rvNews;

    EmptyViewPod vpEmpty;


    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter mNewsAdapter;


    private void init() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        mToolbar = findViewById(R.id.toolbar);
        rvNews = findViewById(R.id.rv_news);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        nsvBottomSheet = findViewById(R.id.nsv_bottom_sheet);
    }

    private SmartScrollListener mSmartScrollListener;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        newsListPresenter = new NewsListPresenter(this);
        mToolbar.setTitle(R.string.title_latest_news);
        newsListPresenter.onCreate();

        setSupportActionBar(mToolbar);
        newsListPresenter.onCreate();
        newsListPresenter.onUiReady();

        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_lastest_news:
                        newsListPresenter.onTapLatestNews();
                        break;
                    case R.id.menu_news_just_for_you:
                        newsListPresenter.onTapJustForYouNews();
                        break;
                    case R.id.menu_favorite_news:
                        newsListPresenter.onTapFavNews();
                        break;
                }
                for (int index = 0; index < mNavigationView.getMenu().size(); index++) {
                    mNavigationView.getMenu().getItem(index).setChecked(false);
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               /* mNewsModel.getNews(new INewsModel.NewsDelegate() {
                    @Override
                    public void onSuccess(List<? extends NewsVO> newsList) {
                        mNewsAdapter.setNewData((List<NewsVO>) newsList);
                        swipeRefreshLayout.setRefreshing(false);
                        newsListPresenter.onRefresh();
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }, true);*/
            }
        });


        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Toast.makeText(getApplicationContext(), "onListEndReach", Toast.LENGTH_LONG).show();
                newsListPresenter.onListEndReach();
            }
        });

        rvNews.addOnScrollListener(mSmartScrollListener);
        rvNews.setEmptyView(vpEmpty);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false));

        mNewsAdapter = new NewsAdapter(newsListPresenter);
        rvNews.setAdapter(mNewsAdapter);

       /* LoginUserViewPod vpLoginUser = (LoginUserViewPod) mNavigationView.getHeaderView(0);
        vpLoginUser.setData(mUserModel.getLoginUser());
        vpLoginUser.setDelegate(new LoginUserViewPod.LoginUserViewPodDelegate() {
            @Override
            public void onTapLogout() {
                mUserModel.onUserLogout();
                navigateToLoginScreen();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  /*  @OnClick(R.id.fab)
    public void onTapFab(View view) {
        *//*
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                *//*

        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }*/


    private void navigateToLoginScreen() {
        Intent intent = LoginActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void showNewsList(List<? extends NewsVO> newsList) {
        mNewsAdapter.setNewData((List<NewsVO>) newsList);
    }


    @Override
    public void showMoreNewsList(@NotNull List<? extends NewsVO> newsList) {
        mNewsAdapter.appendNewData((List<NewsVO>) newsList);
    }

    @Override
    public void nevigateTo(String id) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext());
        intent.putExtra(AppConstants.NEWS_ID, id);
        startActivity(intent);
    }

    @Override
    public void nevigateToJustForYouNews() {
        Toast.makeText(getApplicationContext(), getString(R.string.title_news_just_for_you), Toast.LENGTH_SHORT).show();
        mToolbar.setTitle(R.string.title_news_just_for_you);

    }

    @Override
    public void nevigateToFavNews() {
        Toast.makeText(getApplicationContext(), getString(R.string.title_favorite_news), Toast.LENGTH_SHORT).show();
        mToolbar.setTitle(R.string.title_favorite_news);

    }

    @Override
    public void nevigateToLatestNews() {
        Toast.makeText(getApplicationContext(), getString(R.string.title_latest_news), Toast.LENGTH_SHORT).show();
        mToolbar.setTitle(R.string.title_latest_news);

    }

    @Override
    public void nevigateToLogin() {
        navigateToLoginScreen();
        return;
    }


}
