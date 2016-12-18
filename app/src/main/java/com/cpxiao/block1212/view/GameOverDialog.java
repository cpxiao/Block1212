package com.cpxiao.block1212.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.block1212.R;

/**
 * @author cpxiao on 2015/10/22.
 */
public class GameOverDialog extends Dialog {
    private TextView mBtnOK;
    private TextView mBtnCancel;


    public GameOverDialog(Context context) {
        super(context, R.style.ScoreDialog);
        initWidget();

    }

    private void initWidget() {
        setContentView(R.layout.dialog_game_over);
        // 点击外面区域不会让dialog消失
        setCanceledOnTouchOutside(false);
        setCancelable(true);


        mBtnOK = (TextView) findViewById(R.id.dialog_btn_ok);
        mBtnCancel = (TextView) findViewById(R.id.dialog_btn_cancel);
    }


    public void setButtonOK(View.OnClickListener click) {
        mBtnOK.setOnClickListener(click);
    }


    public void setButtonCancel(View.OnClickListener click) {
        mBtnCancel.setOnClickListener(click);
    }


}
