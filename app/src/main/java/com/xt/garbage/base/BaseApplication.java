package com.xt.garbage.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class BaseApplication extends Application {

    public static Context appContext;
    public static ArrayList<Activity> allActivities = new ArrayList<>();
    public static BaseApplication app;
    private boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        app = this;
        if(isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(app);
        MultiDex.install(this);
    }
    public static Context getContext() {
        return appContext;
    }

    public static BaseApplication getApp() {
        return app;
    }

    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        allActivities.remove(activity);
    }
}
