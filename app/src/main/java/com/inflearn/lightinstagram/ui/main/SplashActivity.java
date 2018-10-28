package com.inflearn.lightinstagram.ui.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.ui.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    private Button btnSplash;

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
                Intent intent = new Intent(context, MainActivity.class)
                        .putExtra(MainActivity.NAME_KEY, "maryang");
                startActivity(intent);
                finish();
            }
        });
    }

    private Observable<Boolean> checkPermission() {
        return rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}