package com.cpxiao.free1010plus.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.free1010plus.R;
import com.cpxiao.free1010plus.onGameListener;
import com.cpxiao.free1010plus.utils.ActivityCollector;
import com.cpxiao.free1010plus.utils.Config;
import com.cpxiao.free1010plus.utils.Extra;
import com.cpxiao.free1010plus.utils.SharedPreferencesUtils;
import com.cpxiao.free1010plus.view.GameSurfaceView;
import com.cpxiao.free1010plus.view.MenuDialog;
import com.cpxiao.free1010plus.view.ScoreDialog;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;


/**
 * @author cpxiao on 15/10/20.
 */
public class GameActivity extends BaseActivity implements View.OnClickListener, onGameListener {
    /**
     * 当前分数
     */
    private TextView mScoreView;
    /**
     * 最高分
     */
    private TextView mBestView;
    /**
     * 游戏View
     */
    GameSurfaceView MGameSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏部分（电池电量、时间等部分）
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivityCollector.addActivity(this);

        init();

        if (Config.YOUMI_IF_SHOW_ADS) {
            /**Stats*/
            // 实例化 LayoutParams（重要）
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            // 设置广告条的悬浮位置
            layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL; // 这里示例为右下角
            // 实例化广告条
            AdView adView = new AdView(GameActivity.this, AdSize.FIT_SCREEN);
            // 调用 Activity 的 addContentView 函数
            addContentView(adView, layoutParams);
        }
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            throw new NullPointerException("bundle must not be null!");
        }
        boolean isNewGame = bundle.getBoolean(Extra.INTENT_EXTRA_IS_NEW_GAME, true);

        setContentView(R.layout.activity_game);

        ImageView mPause = (ImageView) findViewById(R.id.btn_pause);
        mPause.setOnClickListener(this);

        mScoreView = (TextView) findViewById(R.id.score);
        mBestView = (TextView) findViewById(R.id.best);

        mScoreView.setText(String.valueOf(0));

        int bestScore = SharedPreferencesUtils.getScore(this);
        mBestView.setText(String.valueOf(bestScore));


        LinearLayout layout = (LinearLayout) findViewById(R.id.game_view);
        MGameSurfaceView = new GameSurfaceView(GameActivity.this, 10, isNewGame);

        MGameSurfaceView.setOnGameListener(this);
        layout.addView(MGameSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MGameSurfaceView.save();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onScoreChange(final int score, final int bestScore) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (score > bestScore) {
                    SharedPreferencesUtils.setScore(getApplicationContext(), score);
                    mBestView.setText(String.valueOf(score));
                }
                mScoreView.setText(String.valueOf(score));
            }
        });
    }

    @Override
    public void onGameOver() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final ScoreDialog dialog = new ScoreDialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle(getResources().getString(R.string.game_over));
                dialog.hideMsg();
                dialog.setSubMsg(getResources().getString(R.string.play_again));
                dialog.setButtonOK("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mScoreView.setText(String.valueOf(0));
                        MGameSurfaceView.restart(GameActivity.this);
                    }
                });
                dialog.setButtonCancel("No", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

                dialog.show();

            }
        });

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_pause) {
            MenuDialog dialog = new MenuDialog(GameActivity.this);
            dialog.show();
        }
    }

    public static void come2me(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
