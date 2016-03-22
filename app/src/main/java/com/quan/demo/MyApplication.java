package com.quan.demo;

import android.app.Application;

/**
 * Created by QB on 2015/9/10.
 */
public class MyApplication extends Application {
    private static MyApplication thiz;
    @Override
    public void onCreate() {
        super.onCreate();
        thiz = this;
    }
    public static MyApplication getContext() {
        return thiz;
    }
}
