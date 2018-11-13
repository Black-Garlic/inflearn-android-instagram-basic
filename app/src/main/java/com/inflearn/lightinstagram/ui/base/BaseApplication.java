package com.inflearn.lightinstagram.ui.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BaseApplication extends Application {

    private static BaseApplication app;

    public static BaseApplication get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Stetho.initializeWithDefaults(this);
    }
}