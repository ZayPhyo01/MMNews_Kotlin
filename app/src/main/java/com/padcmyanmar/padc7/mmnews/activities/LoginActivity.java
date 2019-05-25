package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.models.UserModel;
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;
import com.padcmyanmar.padc7.mmnews.mvp.presenter.LoginPresenter;
import com.padcmyanmar.padc7.mmnews.mvp.view.LoginView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView{

    private LoginPresenter loginPresenter;

    Toolbar toolbar;


    EditText etEmailOrPhone;
    Button login;


    EditText etPassword;



    void init() {
        toolbar = findViewById(R.id.toolbar);
        etEmailOrPhone = findViewById(R.id.et_email_or_phone);
        etPassword = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public static Intent newIntentBackKeyEnable(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        loginPresenter= new LoginPresenter(this);


        setSupportActionBar(toolbar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onTaplogin();
            }
        });
    }



    @Override
    public void login(@NotNull LoginUserVO userVO) {
        Intent intent = MainActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
