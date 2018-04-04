package com.cpxiao.block1212.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.MediaPlayerUtils;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.androidutils.library.utils.ThreadUtils;
import com.cpxiao.block1212.imp.onGameListener;
import com.cpxiao.block1212.mode.extra.Extra;
import com.cpxiao.block1212.mode.extra.GameDifficulty;
import com.cpxiao.block1212.views.GameSurfaceView;
import com.cpxiao.block1212.views.dialog.GameOverDialog;
import com.cpxiao.block1212.views.dialog.SettingsDialog;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.zads.core.ZAdPosition;

/**
 * @author cpxiao on 2017/09/06.
 */

public class GameFragment extends BaseZAdsFragment implements onGameListener {

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

    private LinearLayout layout;

    public static GameFragment newInstance(Bundle bundle) {
        GameFragment fragment = new GameFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadZAds(ZAdPosition.POSITION_GAME);

        final Context context = getHoldingActivity();
        MediaPlayerUtils.getInstance().init(context, R.raw.block1212_bgm);

        Bundle bundle = getArguments();
        if (bundle == null) {
            if (DEBUG) {
                throw new NullPointerException("bundle must not be null!");
            }
            return;
        }
        boolean isNewGame = bundle.getBoolean(Extra.Name.INTENT_NAME_IS_NEW_GAME, true);
        mGameDifficulty = bundle.getInt(Extra.Name.INTENT_NAME_DIFFICULTY, GameDifficulty.DEFAULT);

        mGameModeView = (TextView) view.findViewById(R.id.game_mode);
        setGameModeView(mGameDifficulty);

        mScoreView = (TextView) view.findViewById(R.id.score);
        mBestScoreView = (TextView) view.findViewById(R.id.best);
        setScoreView(0);

        updateBestScore(0, mGameDifficulty);

        //设置
        ImageView settingsView = (ImageView) view.findViewById(R.id.btn_settings);
        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SettingsDialog dialog = new SettingsDialog(context);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
        layout = (LinearLayout) view.findViewById(R.id.game_view);
        initGameView(isNewGame);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = getHoldingActivity();
        boolean isMusicOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
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
    public void onDestroy() {
        super.onDestroy();
        MediaPlayerUtils.getInstance().stop();
    }

    private void initGameView(boolean isNewGame) {
        loadZAds(ZAdPosition.POSITION_GAME);
        Context context = getHoldingActivity();
        layout.removeAllViews();
        mGameSurfaceView = new GameSurfaceView(context, 12, isNewGame, mGameDifficulty);
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
        } else if (gameDifficulty == GameDifficulty.INSANE) {
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
        Context context = getHoldingActivity();
        int bestScore = 0;
        if (gameDifficulty == GameDifficulty.EASY) {
            bestScore = PreferencesUtils.getInt(context, Extra.Key.KEY_BEST_SCORE_EASY, 0);
        } else if (gameDifficulty == GameDifficulty.NORMAL) {
            bestScore = PreferencesUtils.getInt(context, Extra.Key.KEY_BEST_SCORE_NORMAL, 0);
        } else if (gameDifficulty == GameDifficulty.HARD) {
            bestScore = PreferencesUtils.getInt(context, Extra.Key.KEY_BEST_SCORE_HARD, 0);
        } else if (gameDifficulty == GameDifficulty.INSANE) {
            bestScore = PreferencesUtils.getInt(context, Extra.Key.KEY_BEST_SCORE_INSANE, 0);
        }
        if (score > bestScore) {
            bestScore = score;
            if (gameDifficulty == GameDifficulty.EASY) {
                PreferencesUtils.putInt(context, Extra.Key.KEY_BEST_SCORE_EASY, score);
            } else if (gameDifficulty == GameDifficulty.NORMAL) {
                PreferencesUtils.putInt(context, Extra.Key.KEY_BEST_SCORE_NORMAL, score);
            } else if (gameDifficulty == GameDifficulty.HARD) {
                PreferencesUtils.putInt(context, Extra.Key.KEY_BEST_SCORE_HARD, score);
            } else if (gameDifficulty == GameDifficulty.INSANE) {
                PreferencesUtils.putInt(context, Extra.Key.KEY_BEST_SCORE_INSANE, score);
            }
        }
        setBestScoreView(bestScore);
    }


    @Override
    public void onScoreChange(final int score) {
        ThreadUtils.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setScoreView(score);
                updateBestScore(score, mGameDifficulty);
            }
        });
    }

    @Override
    public void onGameOver() {
        final Context context = getHoldingActivity();
        ThreadUtils.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final GameOverDialog dialog = new GameOverDialog(context);
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
                        removeFragment();
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

}
