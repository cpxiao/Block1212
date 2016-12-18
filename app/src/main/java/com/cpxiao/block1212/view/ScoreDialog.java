package com.cpxiao.block1212.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.block1212.R;

/**
 * @author cpxiao on 2015/10/22.
 */
public class ScoreDialog extends Dialog {
    private TextView mTitle;


    private TextView mMsg;
    private TextView mSubMsg;


    private TextView mBtnOK;
    private TextView mBtnCancel;


    public ScoreDialog(Context context) {
        super(context, R.style.ScoreDialog);
        initWidget();

    }

    private void initWidget() {
        setContentView(R.layout.dialog_score);
        // 点击外面区域不会让dialog消失
        setCanceledOnTouchOutside(false);

        mTitle = (TextView) findViewById(R.id.dialog_title);
        mMsg = (TextView) findViewById(R.id.dialog_msg);
        mSubMsg = (TextView) findViewById(R.id.dialog_sub_msg);
        mBtnOK = (TextView) findViewById(R.id.dialog_btn_ok);
        mBtnCancel = (TextView) findViewById(R.id.dialog_btn_cancel);
        setCancelable(true);
    }


    public void setTitle(int msg) {
        mTitle.setText(msg);
    }

    public void setTitle(String msg) {
        mTitle.setText(msg);
    }

    public void hideTitle() {
        mTitle.setVisibility(View.GONE);
    }

    public void setMsg(int string) {
        mMsg.setText(string);
    }

    public void setMsg(String string) {
        mMsg.setText(string);
    }

    public void hideMsg() {
        mMsg.setVisibility(View.GONE);
    }

    public void setSubMsg(int string) {
        mSubMsg.setText(string);
    }

    public void setSubMsg(String string) {
        mSubMsg.setText(string);
    }

    public void hideSubMsg() {
        mSubMsg.setVisibility(View.GONE);
    }


    public void setButtonOK(View.OnClickListener click) {
        mBtnOK.setOnClickListener(click);
    }

    public void setButtonOK(int res, View.OnClickListener click) {
        mBtnOK.setText(res);
        mBtnOK.setOnClickListener(click);
    }

    public void setButtonOK(String res, View.OnClickListener click) {
        mBtnOK.setText(res);
        mBtnOK.setOnClickListener(click);
    }

    public void hideButtonOK() {
        mBtnOK.setVisibility(View.GONE);
    }

    public void setButtonCancel(View.OnClickListener click) {
        mBtnCancel.setOnClickListener(click);
    }

    public void setButtonCancel(int res, View.OnClickListener click) {
        mBtnCancel.setText(res);
        mBtnCancel.setOnClickListener(click);
    }

    public void setButtonCancel(String res, View.OnClickListener click) {
        mBtnCancel.setText(res);
        mBtnCancel.setOnClickListener(click);
    }

    public void hideButtonCancel() {
        mBtnCancel.setVisibility(View.GONE);
    }

}
