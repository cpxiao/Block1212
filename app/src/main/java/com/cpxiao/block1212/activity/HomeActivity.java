package com.cpxiao.block1212.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cpxiao.R;
import com.cpxiao.block1212.Extra;
import com.cpxiao.block1212.views.dialog.BestScoreDialog;
import com.cpxiao.gamelib.activity.BaseActivity;

/**
 * @author cpxiao on 2015/10/20.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initWidget();
        initFbAds50("236636880101032_236637330100987");
        initAdMobAds50("ca-app-pub-4157365005379790/3703396465");
    }

    private void initWidget() {

        Button btnEasy = (Button) findViewById(R.id.btn_easy);
        btnEasy.setOnClickListener(this);

        Button btnNormal = (Button) findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(this);

        Button btnHard = (Button) findViewById(R.id.btn_hard);
        btnHard.setOnClickListener(this);

        Button btnInsane = (Button) findViewById(R.id.btn_insane);
        btnInsane.setOnClickListener(this);

        Button btnBestScore = (Button) findViewById(R.id.btn_best_score);
        btnBestScore.setOnClickListener(this);

        Button btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(this);

        Button btnQuti = (Button) findViewById(R.id.btn_quit);
        btnQuti.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_easy) {
            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.EASY);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.btn_normal) {
            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.NORMAL);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.btn_hard) {
            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.HARD);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.btn_insane) {
            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.INSANE);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.btn_best_score) {
            final BestScoreDialog dialog = new BestScoreDialog(HomeActivity.this);
            dialog.setButtonOK(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else if (id == R.id.btn_settings) {
            Intent intent = SettingsActivity.makeIntent(HomeActivity.this, null);
            startActivity(intent);
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
