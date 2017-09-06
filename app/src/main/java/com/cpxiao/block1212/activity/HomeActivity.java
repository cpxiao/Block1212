//package com.cpxiao.block1212.activity;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.cpxiao.R;
//import com.cpxiao.block1212.mode.extra.Extra;
//import com.cpxiao.block1212.views.dialog.BestScoreDialog;
//import com.cpxiao.gamelib.activity.BaseZAdsActivity;
//import com.cpxiao.zads.ZAdManager;
//import com.cpxiao.zads.core.ZAdPosition;
//
///**
// * @author cpxiao on 2015/10/20.
// */
//public class HomeActivity extends BaseZAdsActivity implements View.OnClickListener {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//
//        initWidget();
//        //        initFbAds50("236636880101032_236637330100987");
//        //        initAdMobAds50("ca-app-pub-4157365005379790/3703396465");
//
//        ZAdManager.getInstance().init(this);
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_ads);
//        ZAdManager.getInstance().loadAd(this, ZAdPosition.POSITION_HOME, layout);
//    }
//
//    @Override
//    protected void onDestroy() {
//        ZAdManager.getInstance().destroyAllPosition(this);
//        super.onDestroy();
//    }
//
//    private void initWidget() {
//
//        Button btnEasy = (Button) findViewById(R.id.btn_easy);
//        btnEasy.setOnClickListener(this);
//
//        Button btnNormal = (Button) findViewById(R.id.btn_normal);
//        btnNormal.setOnClickListener(this);
//
//        Button btnHard = (Button) findViewById(R.id.btn_hard);
//        btnHard.setOnClickListener(this);
//
//        Button btnInsane = (Button) findViewById(R.id.btn_insane);
//        btnInsane.setOnClickListener(this);
//
//        Button btnBestScore = (Button) findViewById(R.id.btn_best_score);
//        btnBestScore.setOnClickListener(this);
//
//        Button btnSettings = (Button) findViewById(R.id.btn_settings);
//        btnSettings.setOnClickListener(this);
//
//        Button btnQuit = (Button) findViewById(R.id.btn_quit);
//        btnQuit.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        if (id == R.id.btn_easy) {
//            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.EASY);
//            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
//            startActivity(intent);
//        } else if (id == R.id.btn_normal) {
//            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.NORMAL);
//            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
//            startActivity(intent);
//        } else if (id == R.id.btn_hard) {
//            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.HARD);
//            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
//            startActivity(intent);
//        } else if (id == R.id.btn_insane) {
//            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.INSANE);
//            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
//            startActivity(intent);
//        } else if (id == R.id.btn_best_score) {
//            final BestScoreDialog dialog = new BestScoreDialog(HomeActivity.this);
//            dialog.setButtonOK(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//        } else if (id == R.id.btn_settings) {
//            Intent intent = SettingsActivity.makeIntent(HomeActivity.this, null);
//            startActivity(intent);
//        } else if (id == R.id.btn_quit) {
//            showQuitConfirmDialog();
//        }
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        //        super.onBackPressed();
//        showQuitConfirmDialog();
//    }
//
//    private void showQuitConfirmDialog() {
//        Dialog dialog = new AlertDialog.Builder(this)
//                //                .setTitle(R.string.quit_msg)
//                .setMessage(R.string.quit_msg)
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        finish();
//                    }
//                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        //            dialog.setCancelable(true);
//        //            dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//    }
//}
