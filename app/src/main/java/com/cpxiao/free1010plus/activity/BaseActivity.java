package com.cpxiao.free1010plus.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cpxiao.free1010plus.utils.Config;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;


/**
 * Created by cpxiao on 3/14/16.
 * BaseActivity
 */
public  class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityCollector.addActivity(this);
        /**
         * 初始化
         */
        if (Config.YOUMI_IF_SHOW_ADS) {
            AdManager.getInstance(BaseActivity.this).init(Config.YOUMI_APPID, Config.YOUMI_APPSECRET, false);
        }
        /**
         * 插屏广告
         */
        if (Config.YOUMI_IF_SHOW_SPOT) {
            SpotManager.getInstance(BaseActivity.this).loadSpotAds();
            SpotManager.getInstance(BaseActivity.this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
            SpotManager.getInstance(BaseActivity.this).setAnimationType(SpotManager.ANIM_SIMPLE);
//            SpotManager.getInstance(BaseActivity.this).showSpotAds(BaseActivity.this);
            SpotManager.getInstance(BaseActivity.this).showSpotAds(BaseActivity.this, new SpotDialogListener() {
                @Override
                public void onShowSuccess() {

                }

                @Override
                public void onShowFailed() {

                }

                @Override
                public void onSpotClosed() {

                }

                @Override
                public void onSpotClick(boolean b) {

                }
            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
//    @Override
//    public void onBackPressed() {
//        // 如果有需要，可以点击后退关闭插播广告。
//        if (!SpotManager.getInstance(BaseActivity.this).disMiss()) {
//            // 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
//            super.onBackPressed();
//        }
//    }

    @Override
    protected void onDestroy() {
        if (Config.YOUMI_IF_SHOW_SPOT) {
            SpotManager.getInstance(BaseActivity.this).onDestroy();
        }
        super.onDestroy();
    }

}
