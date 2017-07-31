package com.cpxiao.block1212.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.block1212.Extra;

/**
 * @author cpxiao on 2015/10/22.
 */
public class BestScoreDialog extends Dialog {

    private TextView mBtnOK;

    public BestScoreDialog(Context context) {
        super(context, R.style.ScoreDialog);
        initWidget();

    }

    private void initWidget() {
        setContentView(R.layout.dialog_best_score);
        // 点击外面区域不会让dialog消失
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        TextView easyScoreTV = (TextView) findViewById(R.id.easy_score);
        String easyScore = String.valueOf(PreferencesUtils.getInt(getContext(), Extra.Key.KEY_BEST_SCORE_EASY, 0));
        easyScoreTV.setText(easyScore);

        TextView normalScoreTV = (TextView) findViewById(R.id.normal_score);
        String normalScore = String.valueOf(PreferencesUtils.getInt(getContext(), Extra.Key.KEY_BEST_SCORE_NORMAL, 0));
        normalScoreTV.setText(normalScore);

        TextView hardScoreTV = (TextView) findViewById(R.id.hard_score);
        String hardScore = String.valueOf(PreferencesUtils.getInt(getContext(), Extra.Key.KEY_BEST_SCORE_HARD, 0));
        hardScoreTV.setText(hardScore);

        TextView insaneScoreTV = (TextView) findViewById(R.id.insane_score);
        String insaneScore = String.valueOf(PreferencesUtils.getInt(getContext(), Extra.Key.KEY_BEST_SCORE_INSANE, 0));
        insaneScoreTV.setText(insaneScore);

        mBtnOK = (TextView) findViewById(R.id.dialog_btn_ok);
    }


    public void setButtonOK(View.OnClickListener click) {
        mBtnOK.setOnClickListener(click);
    }


}
