package com.cpxiao.free1010plus.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by cpxiao on 3/23/16.
 * SettingActivity
 */
public class MoreGamesActivity extends BaseActivity {



    public static void come2me(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MoreGamesActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
