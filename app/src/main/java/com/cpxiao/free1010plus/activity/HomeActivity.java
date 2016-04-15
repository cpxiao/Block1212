package com.cpxiao.free1010plus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.free1010plus.R;
import com.cpxiao.free1010plus.utils.ActivityCollector;
import com.cpxiao.free1010plus.utils.Config;
import com.cpxiao.free1010plus.utils.Extra;
import com.cpxiao.free1010plus.view.ScoreDialog;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView mBestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ActivityCollector.addActivity(this);


        if (Config.YOUMI_IF_SHOW_ADS) {
            /**Stats*/
            // 实例化 LayoutParams（重要）
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            // 设置广告条的悬浮位置
            layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
            // 实例化广告条
            AdView adView = new AdView(HomeActivity.this, AdSize.FIT_SCREEN);
            // 调用 Activity 的 addContentView 函数
            addContentView(adView, layoutParams);
        }
    }

    private void init() {
        LinearLayout continueLayout = (LinearLayout) findViewById(R.id.btn_continue);
        continueLayout.setOnClickListener(this);

        LinearLayout newGameLayout = (LinearLayout) findViewById(R.id.btn_new_game);
        newGameLayout.setOnClickListener(this);

        LinearLayout bestScoreLayout = (LinearLayout) findViewById(R.id.btn_best_score);
        bestScoreLayout.setOnClickListener(this);
        mBestScore = (TextView) findViewById(R.id.best_score);
        mBestScore.setText(getBestScore());

        LinearLayout quitLayout = (LinearLayout) findViewById(R.id.quit);
        quitLayout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBestScore.setText(getBestScore());
    }

    private String getBestScore() {
        SharedPreferences score = getSharedPreferences("score", 0);
        return String.valueOf(score.getInt("bestScore", 0));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_continue) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Extra.INTENT_EXTRA_IS_NEW_GAME, false);
            GameActivity.come2me(HomeActivity.this, bundle);
        } else if (id == R.id.btn_new_game) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Extra.INTENT_EXTRA_IS_NEW_GAME, true);
            GameActivity.come2me(HomeActivity.this, bundle);
        } else if (id == R.id.btn_best_score) {
            String bestScore = getBestScore();

            final ScoreDialog dialog = new ScoreDialog(HomeActivity.this);
            dialog.setTitle(getResources().getString(R.string.best_score));
            dialog.setMsg(bestScore);
            dialog.hideSubMsg();
            dialog.setButtonOK("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.hideButtonCancel();
            dialog.show();

        } else if (id == R.id.more_games) {

        } else if (id == R.id.quit) {
            ActivityCollector.finishAll();
        }
    }


    public static void come2me(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
