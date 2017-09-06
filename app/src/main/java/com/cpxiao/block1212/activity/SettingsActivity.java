//package com.cpxiao.block1212.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.cpxiao.R;
//import com.cpxiao.androidutils.library.utils.PreferencesUtils;
//import com.cpxiao.block1212.mode.extra.Extra;
//import com.cpxiao.gamelib.activity.BaseZAdsActivity;
//import com.cpxiao.zads.ZAdManager;
//import com.cpxiao.zads.core.ZAdPosition;
//
///**
// * @author cpxiao on 2017/02/08.
// */
//public class SettingsActivity extends BaseZAdsActivity implements View.OnClickListener {
//
//    private TextView mSound;
//    private TextView mMusic;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_settings);
//        initWidget();
//        //        initFbAds50("299750750363934_300182613654081");
//        //        initAdMobAds50("ca-app-pub-4157365005379790/8151240158");
//
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_ads);
//        ZAdManager.getInstance().loadAd(this, ZAdPosition.POSITION_SETTINGS, layout);
//    }
//
//    @Override
//    protected void onDestroy() {
//        ZAdManager.getInstance().destroy(this, ZAdPosition.POSITION_SETTINGS);
//        super.onDestroy();
//    }
//
//    private void initWidget() {
//        findViewById(R.id.layout_sound).setOnClickListener(this);
//        findViewById(R.id.layout_music).setOnClickListener(this);
//        mSound = (TextView) findViewById(R.id.tv_sound);
//        mMusic = (TextView) findViewById(R.id.tv_music);
//
//        boolean isSoundOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_SOUND, Extra.Key.SETTING_SOUND_DEFAULT);
//        if (isSoundOn) {
//            mSound.setText(R.string.settings_on);
//        } else {
//            mSound.setText(R.string.settings_off);
//        }
//
//        boolean isMusicOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
//        if (isMusicOn) {
//            mMusic.setText(R.string.settings_on);
//        } else {
//            mMusic.setText(R.string.settings_off);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        if (id == R.id.layout_sound) {
//            boolean isSoundOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_SOUND, Extra.Key.SETTING_SOUND_DEFAULT);
//            if (isSoundOn) {
//                PreferencesUtils.putBoolean(getApplicationContext(), Extra.Key.SETTING_SOUND, false);
//                mSound.setText(R.string.settings_off);
//            } else {
//                PreferencesUtils.putBoolean(getApplicationContext(), Extra.Key.SETTING_SOUND, true);
//                mSound.setText(R.string.settings_on);
//            }
//        } else if (id == R.id.layout_music) {
//            boolean isMusicOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
//            if (isMusicOn) {
//                PreferencesUtils.putBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, false);
//                mMusic.setText(R.string.settings_off);
//            } else {
//                PreferencesUtils.putBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, true);
//                mMusic.setText(R.string.settings_on);
//            }
//        }
//    }
//
//    public static Intent makeIntent(Context context, Bundle bundle) {
//        Intent intent = new Intent(context, SettingsActivity.class);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        return intent;
//    }
//
//
//    public static Bundle makeBundle() {
//        return null;
//    }
//
//
//}
