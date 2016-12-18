package com.cpxiao.block1212.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.block1212.R;
import com.cpxiao.block1212.utils.Extra;
import com.cpxiao.block1212.view.ScoreDialog;

/**
 * @author cpxiao on 2015/10/20.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        Button btnContinue = (Button) findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(this);
        btnContinue.setVisibility(View.GONE);

        Button btnNewGame = (Button) findViewById(R.id.btn_new_game);
        btnNewGame.setOnClickListener(this);

        Button btnBestScore = (Button) findViewById(R.id.btn_best_score);
        btnBestScore.setOnClickListener(this);

        Button btnQuit = (Button) findViewById(R.id.btn_quit);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_continue) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Extra.INTENT_NAME_IS_NEW_GAME, false);
            GameActivity.come2me(HomeActivity.this, bundle);
        } else if (id == R.id.btn_new_game) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Extra.INTENT_NAME_IS_NEW_GAME, true);
            GameActivity.come2me(HomeActivity.this, bundle);
        } else if (id == R.id.btn_best_score) {
            String bestScore = String.valueOf(PreferencesUtils.getInt(this, Extra.KEY_BEST_SCORE, 0));
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


        } else if (id == R.id.btn_quit) {
            finish();
        }
    }


    public static void come2me(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
