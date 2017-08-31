package com.kyros.technologies.railapp.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by kyros on 19-07-2017.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
     }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
