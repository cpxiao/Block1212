package com.cpxiao.block1212.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.RateAppUtils;
import com.cpxiao.androidutils.library.utils.ShareAppUtils;
import com.cpxiao.block1212.mode.extra.GameDifficulty;
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
        ImageButton btnRateApp = (ImageButton) view.findViewById(R.id.btn_rate_app);
        ImageButton btnShare = (ImageButton) view.findViewById(R.id.btn_share);
        ImageButton btnBestScore = (ImageButton) view.findViewById(R.id.btn_best_score);
        ImageButton btnSettings = (ImageButton) view.findViewById(R.id.btn_settings);

        btnEasy.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnHard.setOnClickListener(this);
        btnInsane.setOnClickListener(this);
        btnRateApp.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnBestScore.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
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
            Bundle bundle = GameFragment.makeBundle(true, GameDifficulty.EASY);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_normal) {
            Bundle bundle = GameFragment.makeBundle(true, GameDifficulty.NORMAL);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_hard) {
            Bundle bundle = GameFragment.makeBundle(true, GameDifficulty.HARD);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_insane) {
            Bundle bundle = GameFragment.makeBundle(true, GameDifficulty.INSANE);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.btn_rate_app) {
            RateAppUtils.rate(context);
        } else if (id == R.id.btn_share) {
            String msg = getString(R.string.share_msg) + "\n" +
                    getString(R.string.app_name) + "\n" +
                    "https://play.google.com/store/apps/details?id=" + context.getPackageName();
            ShareAppUtils.share(context,getString(R.string.share), msg);
        } else if (id == R.id.btn_best_score) {
            showBestScoreDialog(context);
        } else if (id == R.id.btn_settings) {
            addFragment(SettingsFragment.newInstance(null));
        }
    }

    private void showBestScoreDialog(Context context) {
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
    }

}
