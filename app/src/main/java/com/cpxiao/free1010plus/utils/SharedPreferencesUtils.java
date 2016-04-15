package com.cpxiao.free1010plus.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cpxiao on 1/28/16.
 * SharedPreferencesUtils
 */
public class SharedPreferencesUtils {

    private final static String NAME = "score";
    private final static String KEY_SCORE = "bestScore";

    public static int getScore(Context c) {
        SharedPreferences score = c.getSharedPreferences(NAME, 0);
        return score.getInt(KEY_SCORE, 0);
    }

    public static void setScore(Context c, int score) {
        SharedPreferences preferences = c.getSharedPreferences(NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_SCORE, score);
        editor.commit();
//        editor.apply();
    }
}
