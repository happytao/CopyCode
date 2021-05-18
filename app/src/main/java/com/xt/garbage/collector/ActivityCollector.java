package com.xt.garbage.collector;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/23
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 向List中添加一个活动
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 从list中移除活动
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 销毁所有活动
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
