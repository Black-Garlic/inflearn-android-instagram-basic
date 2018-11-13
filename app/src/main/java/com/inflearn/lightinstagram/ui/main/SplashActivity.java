package com.inflearn.lightinstagram.ui.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.data.source.UserLocalSource;
import com.inflearn.lightinstagram.ui.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    private Button btnSplash;
    private UserLocalSource userLocalSource = new UserLocalSource();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        setButton();

        checkPermission().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) {
                if (!granted) {
                    Toast.makeText(context, R.string.splash_permission, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void findView() {
        btnSplash = findViewById(R.id.btn_splash);
    }

    private void setButton() {
        btnSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "maryang";
                User user = userLocalSource.getMaster();
                if (user == null) createMaster(name);
                Intent intent = new Intent(context, MainActivity.class)
                        .putExtra(MainActivity.NAME_KEY, name);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createMaster(String name) {
        String avatarUrl = "https://avatars1.githubusercontent.com/u/4494892?s=400&u=14d2eeb859aaa512c81748948585400b70f57471&v=4";
        User user = new User(name, avatarUrl, true);
        userLocalSource.insert(user);
    }

    private Observable<Boolean> checkPermission() {
        return rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}