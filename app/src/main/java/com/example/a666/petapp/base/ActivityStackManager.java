package com.example.a666.petapp.base;

import android.app.Activity;

import java.util.Stack;

/**
 *  916464 2017-12-06.
 * Activity管理类
 */
public class ActivityStackManager {
    private static Stack<Activity> activities;
    private static ActivityStackManager instance;

    public ActivityStackManager() {

    }

    public synchronized static ActivityStackManager getActivityStackManager() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * 关闭activity
     */
    public void popActivity(Activity activity) {
        if (activities == null) return;
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(0, 0);
            activities.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前的Activity
     */
    public Activity currentActivity() {
        if (activities == null || activities.isEmpty()) return null;
        Activity activity = (Activity) activities.lastElement();
        return activity;
    }

    /**
     * 获取最后一个的Activity
     */
    public Activity firstActivity() {
        if (activities == null || activities.isEmpty()) return null;
        Activity activity = (Activity) activities.firstElement();
        return activity;
    }

    /**
     * 添加activity到Stack
     */
    public void pushActivity(Activity activity) {
        if (activities == null) {
            activities = new Stack<Activity>();
        }
        activities.add(activity);
    }

    /**
     * remove所有Activity
     * remove all activity.
     */
    public void popAllActivity() {
        if (activities == null) return;
        while (true) {
            if (activities.empty()) {
                break;
            }
            Activity activity = currentActivity();
            popActivity(activity);
        }
    }

    /**
     * remove所有Activity但保持目前的Activity
     */
    public void popAllActivityWithOutCurrent() {
        Activity activity = currentActivity();
        while (true) {
            if (activities.size() == 1) {
                break;
            }
            if (firstActivity() == activity) {
                break;
            } else {
                popActivity(firstActivity());
            }
        }
    }
}
