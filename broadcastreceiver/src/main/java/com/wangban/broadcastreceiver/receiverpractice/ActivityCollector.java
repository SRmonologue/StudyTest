package com.wangban.broadcastreceiver.receiverpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：管理所有活动的类
 * Created by 9527 on 2018/5/3.
 */

public class ActivityCollector {
    public static List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
