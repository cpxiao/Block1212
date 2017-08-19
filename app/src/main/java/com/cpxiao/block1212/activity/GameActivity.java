package com.cpxiao.block1212.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.MediaPlayerUtils;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.block1212.imp.onGameListener;
import com.cpxiao.block1212.mode.extra.Extra;
import com.cpxiao.block1212.views.GameSurfaceView;
import com.cpxiao.block1212.views.dialog.GameOverDialog;
import com.cpxiao.block1212.views.dialog.SettingsDialog;
import com.cpxiao.gamelib.activity.BaseZAdsActivity;
import com.cpxiao.zads.ZAdManager;
import com.cpxiao.zads.core.ZAdPosition;


/**
 * @author cpxiao on 2015/10/20.
 */
public class GameActivity extends BaseZAdsActivity implements onGameListener {
    /**
     * 游戏难度
     */
    private int mGameDifficulty;
    /**
     * 游戏模式
     */
    private TextView mGameModeView;
    /**
     * 当前分数
     */
    private TextView mScoreView;
    /**
     * 最高分
     */
    private TextView mBestScoreView;
    /**
     * 游戏View
     */
    private GameSurfaceView mGameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MediaPlayerUtils.getInstance().init(this, R.raw.block1212_bgm);

        setContentView(R.layout.activity_game);
        initWidget();
        //        initFbAds50("236636880101032_236637590100961");
        //        initAdMobAds50("ca-app-pub-4157365005379790/7898093661");

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_ads);
        ZAdManager.getInstance().loadAd(this, ZAdPosition.POSITION_GAME, layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isMusicOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
        if (isMusicOn) {
            MediaPlayerUtils.getInstance().start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MediaPlayerUtils.getInstance().pause();
        mGameSurfaceView.save();
    }

    @Override
    protected void onDestroy() {
        ZAdManager.getInstance().destroy(this, ZAdPosition.POSITION_GAME);
        super.onDestroy();
        MediaPlayerUtils.getInstance().stop();
    }

    private void initWidget() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            if (DEBUG) {
                throw new NullPointerException("bundle must not be null!");
            }
            return;
        }
        boolean isNewGame = bundle.getBoolean(Extra.Name.INTENT_NAME_IS_NEW_GAME, true);
        mGameDifficulty = bundle.getInt(Extra.Name.INTENT_NAME_DIFFICULTY, Extra.GameDifficulty.DEFAULT);

        mGameModeView = (TextView) findViewById(R.id.game_mode);
        setGameModeView(mGameDifficulty);

        mScoreView = (TextView) findViewById(R.id.score);
        mBestScoreView = (TextView) findViewById(R.id.best);
        setScoreView(0);

        updateBestScore(0, mGameDifficulty);

        //设置
        ImageView settingsView = (ImageView) findViewById(R.id.btn_settings);
        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SettingsDialog dialog = new SettingsDialog(GameActivity.this);
                dialog.show();
            }
        });
        initGameView(isNewGame);
    }

    private void initGameView(boolean isNewGame) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.game_view);
        layout.removeAllViews();
        mGameSurfaceView = new GameSurfaceView(GameActivity.this, 12, isNewGame, mGameDifficulty);
        mGameSurfaceView.setOnGameListener(this);
        layout.addView(mGameSurfaceView);
    }

    private void setGameModeView(int gameDifficulty) {
        String text = "";
        if (gameDifficulty == Extra.GameDifficulty.EASY) {
            text = getResources().getString(R.string.easy);
        } else if (gameDifficulty == Extra.GameDifficulty.NORMAL) {
            text = getResources().getString(R.string.normal);
        } else if (gameDifficulty == Extra.GameDifficulty.HARD) {
            text = getResources().getString(R.string.hard);
        } else if (gameDifficulty == Extra.GameDifficulty.INSANE) {
            text = getResources().getString(R.string.insane);
        }
        text = text + " " + getResources().getString(R.string.mode);
        mGameModeView.setText(text);
    }

    private void setScoreView(int score) {
        String text = String.valueOf(score);
        mScoreView.setText(text);
    }

    private void setBestScoreView(int score) {
        String text = getResources().getString(R.string.best) + ": " + score;
        mBestScoreView.setText(text);
    }

    private void updateBestScore(int score, int gameDifficulty) {
        int bestScore = 0;
        if (gameDifficulty == Extra.GameDifficulty.EASY) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_EASY, 0);
        } else if (gameDifficulty == Extra.GameDifficulty.NORMAL) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_NORMAL, 0);
        } else if (gameDifficulty == Extra.GameDifficulty.HARD) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_HARD, 0);
        } else if (gameDifficulty == Extra.GameDifficulty.INSANE) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_INSANE, 0);
        }
        if (score > bestScore) {
            bestScore = score;
            if (gameDifficulty == Extra.GameDifficulty.EASY) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_EASY, score);
            } else if (gameDifficulty == Extra.GameDifficulty.NORMAL) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_NORMAL, score);
            } else if (gameDifficulty == Extra.GameDifficulty.HARD) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_HARD, score);
            } else if (gameDifficulty == Extra.GameDifficulty.INSANE) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.Key.KEY_BEST_SCORE_INSANE, score);
            }
        }
        setBestScoreView(bestScore);
    }


    @Override
    public void onScoreChange(final int score) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setScoreView(score);
                updateBestScore(score, mGameDifficulty);
            }
        });
    }

    @Override
    public void onGameOver() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final GameOverDialog dialog = new GameOverDialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setButtonOK(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        setScoreView(0);
                        initGameView(true);
                    }
                });
                dialog.setButtonCancel(new View.OnClickListener() {
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


    public static Bundle makeBundle(boolean newGame, int gameDifficulty) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Extra.Name.INTENT_NAME_IS_NEW_GAME, newGame);
        bundle.putInt(Extra.Name.INTENT_NAME_DIFFICULTY, gameDifficulty);
        return bundle;
    }

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

}
