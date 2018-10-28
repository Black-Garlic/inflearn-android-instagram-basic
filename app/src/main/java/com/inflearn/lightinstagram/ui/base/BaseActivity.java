package com.inflearn.lightinstagram.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.inflearn.lightinstagram.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    protected Context context;
    protected RxPermissions rxPermissions;
    private Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = this;
        rxPermissions = new RxPermissions(this);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) initializeToolbar(toolbar);
    }

    protected void initializeToolbar(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}