package com.cpxiao.block1212.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.block1212.R;
import com.cpxiao.block1212.onGameListener;
import com.cpxiao.block1212.utils.Extra;
import com.cpxiao.block1212.view.GameSurfaceView;
import com.cpxiao.block1212.view.ScoreDialog;


/**
 * @author cpxiao on 2015/10/20.
 */
public class GameActivity extends BaseActivity implements onGameListener {
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

        setContentView(R.layout.activity_game);
        init();

    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            throw new NullPointerException("bundle must not be null!");
        }
        boolean isNewGame = bundle.getBoolean(Extra.INTENT_NAME_IS_NEW_GAME, true);

        mScoreView = (TextView) findViewById(R.id.score);
        mBestScoreView = (TextView) findViewById(R.id.best);
        setScoreView(0);

        int bestScore = PreferencesUtils.getInt(this, Extra.KEY_BEST_SCORE,0);
        setBestScoreView(bestScore);

        LinearLayout layout = (LinearLayout) findViewById(R.id.game_view);
        mGameSurfaceView = new GameSurfaceView(GameActivity.this, 12, isNewGame);

        mGameSurfaceView.setOnGameListener(this);
        layout.addView(mGameSurfaceView);
    }

    private void setScoreView(int score) {
        String text = String.valueOf(score);
        mScoreView.setText(text);
    }

    private void setBestScoreView(int score) {
        String text = getResources().getString(R.string.best) + ": " + score;
        mBestScoreView.setText(text);
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
    public void onScoreChange(final int score, final int bestScore) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (score > bestScore) {
                    PreferencesUtils.putInt(getApplicationContext(), Extra.KEY_BEST_SCORE, score);
                    setBestScoreView(score);
                }
                setScoreView(score);
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
                        setScoreView(0);
                        mGameSurfaceView.restart(GameActivity.this);
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


    public static void come2me(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
