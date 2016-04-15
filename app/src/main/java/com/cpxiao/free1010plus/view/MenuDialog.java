package com.cpxiao.free1010plus.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cpxiao.free1010plus.R;
import com.cpxiao.free1010plus.activity.GameActivity;
import com.cpxiao.free1010plus.utils.ActivityCollector;
import com.cpxiao.free1010plus.utils.Extra;


/**
 * Created by cpxiao on 1/28/16.
 * 菜单Dialog
 */
public class MenuDialog extends Dialog implements View.OnClickListener {
    ImageView mContinue;
    ImageView mRestart;
    ImageView mHome;

    public MenuDialog(Context context) {
        super(context, R.style.MenuDialog);
//        super(context, android.R.style.Theme);

        setOwnerActivity((Activity) context);
        init();
    }


    private void init() {
        setContentView(R.layout.layout_menu_dialog);
        // 点击外面区域不会让dialog消失
        setCanceledOnTouchOutside(false);
        initWidget();
    }

    private void initWidget() {
        mContinue = (ImageView) findViewById(R.id.btn_continue);
        mContinue.setOnClickListener(this);

        mRestart = (ImageView) findViewById(R.id.btn_restart);
        mRestart.setOnClickListener(this);

        mHome = (ImageView) findViewById(R.id.btn_home);
        mHome.setOnClickListener(this);

    }

    /**
     * 分享按钮
     */

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_continue) {
            dismiss();
        } else if (id == R.id.btn_restart) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Extra.INTENT_EXTRA_IS_NEW_GAME, true);
            GameActivity.come2me(getContext(), bundle);
        } else if (id == R.id.btn_home) {
//            HomeActivity.come2me(getContext());
            dismiss();
            ActivityCollector.finishAll();
        }

    }
}
