package com.cpxiao.free1010plus.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpxiao on 3/1/16.
 * 新增活动管理器，用于随时随地退出程序
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
