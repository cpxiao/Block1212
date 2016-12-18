package com.cpxiao.block1212.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.block1212.R;
import com.cpxiao.block1212.imp.onGameListener;
import com.cpxiao.block1212.utils.Extra;
import com.cpxiao.block1212.utils.GameDifficulty;
import com.cpxiao.block1212.view.GameSurfaceView;
import com.cpxiao.block1212.view.GameOverDialog;


/**
 * @author cpxiao on 2015/10/20.
 */
public class GameActivity extends BaseActivity implements onGameListener {
    /**
     * 游戏难度
     */
    private int mGameDifficulty;
    /**
     * 当前分数
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
     *
     */
    private LinearLayout layout;
    /**
     * 游戏View
     */
    private GameSurfaceView mGameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        init();
        initSmallAds("236636880101032_236637590100961");
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            throw new NullPointerException("bundle must not be null!");
        }
        boolean isNewGame = bundle.getBoolean(Extra.INTENT_NAME_IS_NEW_GAME, true);
        mGameDifficulty = bundle.getInt(Extra.INTENT_NAME_DIFFICULTY, GameDifficulty.DEFAULT);

        mGameModeView = (TextView) findViewById(R.id.game_mode);
        setGameModeView(mGameDifficulty);

        mScoreView = (TextView) findViewById(R.id.score);
        mBestScoreView = (TextView) findViewById(R.id.best);
        setScoreView(0);

        updateBestScore(0, mGameDifficulty);

        layout = (LinearLayout) findViewById(R.id.game_view);
        initGameView(isNewGame);
    }

    private void initGameView(boolean isNewGame) {
        layout.removeAllViews();
        mGameSurfaceView = new GameSurfaceView(GameActivity.this, 12, isNewGame, mGameDifficulty);
        mGameSurfaceView.setOnGameListener(this);
        layout.addView(mGameSurfaceView);
    }

    private void setGameModeView(int gameDifficulty) {
        String text = "";
        if (gameDifficulty == GameDifficulty.EASY) {
            text = getResources().getString(R.string.easy);
        } else if (gameDifficulty == GameDifficulty.NORMAL) {
            text = getResources().getString(R.string.normal);
        } else if (gameDifficulty == GameDifficulty.HARD) {
            text = getResources().getString(R.string.hard);
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
        if (gameDifficulty == GameDifficulty.EASY) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.KEY_BEST_SCORE_EASY, 0);
        } else if (gameDifficulty == GameDifficulty.NORMAL) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.KEY_BEST_SCORE_NORMAL, 0);
        } else if (gameDifficulty == GameDifficulty.HARD) {
            bestScore = PreferencesUtils.getInt(getApplicationContext(), Extra.KEY_BEST_SCORE_HARD, 0);
        }
        if (score > bestScore) {
            bestScore = score;
            if (gameDifficulty == GameDifficulty.EASY) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.KEY_BEST_SCORE_EASY, score);
            } else if (gameDifficulty == GameDifficulty.NORMAL) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.KEY_BEST_SCORE_NORMAL, score);
            } else if (gameDifficulty == GameDifficulty.HARD) {
                PreferencesUtils.putInt(getApplicationContext(), Extra.KEY_BEST_SCORE_HARD, score);
            }
        }
        setBestScoreView(bestScore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameSurfaceView.save();
    }

    @Override
    protected void onResume() {
        super.onResume();
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


    public static void comeToMe(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
