package com.cpxiao.block1212.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cpxiao.androidutils.library.utils.StringUtils;
import com.cpxiao.block1212.R;
import com.cpxiao.block1212.Config;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.umeng.analytics.MobclickAgent;


/**
 * @author cpxiao on 2016/3/14.
 */
public class BaseActivity extends Activity {
    protected static final boolean DEBUG = Config.DEBUG;
    protected final String TAG = "CPXIAO--" + getClass().getSimpleName();

    protected AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //隐藏状态栏部分（电池电量、时间等部分）
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
            mAdView = null;
        }
        super.onDestroy();
    }

    protected void initSmallAds(String placementId) {
        initAds(placementId, AdSize.BANNER_HEIGHT_50);
    }

    protected void initBigAds(String placementId) {
        initAds(placementId, AdSize.RECTANGLE_HEIGHT_250);
    }

    private void initAds(String placeId, AdSize adSize) {
        if (StringUtils.isEmpty(placeId)) {
            return;
        }
        // Instantiate an AdView view
        mAdView = new AdView(this, placeId, adSize);

        // Find the main layout of your activity
        LinearLayout layout = (LinearLayout) findViewById(R.id.ads_layout);

        // Add the ad view to your activity layout
        layout.addView(mAdView);

        mAdView.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                if (DEBUG) {
                    Log.d(TAG, "onError: " + error.getErrorCode() + "," + error.getErrorMessage());
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (DEBUG) {
                    Log.d(TAG, "onAdLoaded: ");
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                if (DEBUG) {
                    Log.d(TAG, "onAdClicked: ");
                }
            }

        });
        if (DEBUG) {
            AdSettings.addTestDevice("7d7fcc8ff3a053e48671f85990f1ab6d");//nexus 5
        }
        // Request to load an ad
        mAdView.loadAd();
    }

}
