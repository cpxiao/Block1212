package com.cpxiao.block1212.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cpxiao.R;
import com.cpxiao.block1212.mode.extra.Extra;
import com.cpxiao.block1212.views.dialog.BestScoreDialog;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.zads.core.ZAdPosition;

/**
 * @author cpxiao on 2017/09/06.
 */

public class HomeFragment extends BaseZAdsFragment implements View.OnClickListener {
    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadZAds(ZAdPosition.POSITION_HOME);

        Button btnEasy = (Button) view.findViewById(R.id.btn_easy);
        Button btnNormal = (Button) view.findViewById(R.id.btn_normal);
        Button btnHard = (Button) view.findViewById(R.id.btn_hard);
        Button btnInsane = (Button) view.findViewById(R.id.btn_insane);
        Button btnBestScore = (Button) view.findViewById(R.id.btn_best_score);
        Button btnSettings = (Button) view.findViewById(R.id.btn_settings);
        Button btnQuit = (Button) view.findViewById(R.id.btn_quit);

        btnEasy.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnHard.setOnClickListener(this);
        btnInsane.setOnClickListener(this);
        btnBestScore.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Context context = getHoldingActivity();
        if (id == R.id.btn_easy) {

            //            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.EASY);
            //            Intent intent = GameActivity.makeIntent(context, bundle);
            //            startActivity(intent);
            Bundle bundle = GameFragment.makeBundle(true, Extra.GameDifficulty.EASY);
            addFragment(GameFragment.newInstance(bundle));

        } else if (id == R.id.btn_normal) {
            //            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.NORMAL);
            //            Intent intent = GameActivity.makeIntent(context, bundle);
            //            startActivity(intent);
            Bundle bundle = GameFragment.makeBundle(true, Extra.GameDifficulty.NORMAL);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_hard) {
            //            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.HARD);
            //            Intent intent = GameActivity.makeIntent(context, bundle);
            //            startActivity(intent);

            Bundle bundle = GameFragment.makeBundle(true, Extra.GameDifficulty.HARD);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_insane) {
            //            Bundle bundle = GameActivity.makeBundle(true, Extra.GameDifficulty.INSANE);
            //            Intent intent = GameActivity.makeIntent(context, bundle);
            //            startActivity(intent);

            Bundle bundle = GameFragment.makeBundle(true, Extra.GameDifficulty.INSANE);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_best_score) {
            final BestScoreDialog dialog = new BestScoreDialog(context);
            dialog.setButtonOK(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
        } else if (id == R.id.btn_settings) {

            //            Intent intent = SettingsFragment.makeIntent(context, null);
            //            startActivity(intent);

            addFragment(SettingsFragment.newInstance(null));
        } else if (id == R.id.btn_quit) {
            removeFragment();
        }
    }
}
