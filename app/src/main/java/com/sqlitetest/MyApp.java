package com.sqlitetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by 12533 on 2019-4-3.
 */

public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }
}
