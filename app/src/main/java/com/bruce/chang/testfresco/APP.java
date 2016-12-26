package com.bruce.chang.testfresco;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator
 * Date:2016/12/25
 * Time:11:03
 * Author:BruceChang
 * Function:
 */

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
