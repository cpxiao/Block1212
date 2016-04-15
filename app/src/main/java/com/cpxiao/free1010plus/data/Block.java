package com.cpxiao.free1010plus.data;


import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.cpxiao.free1010plus.R;

/**
 * @author cpxiao on 15/10/19.
 */
public class Block {

    /**
     * 数据状态，是否已填充
     */
    public int mData;

    /**
     * 颜色
     */
    public int mColor;

    /**
     * 是否需要重置，在判断是否消除时使用
     */
    public boolean ifNeedReset;

    public Block(Context c) {
        init(c);
    }

    private void init(Context c) {
        mData = 0;
        mColor = ContextCompat.getColor(c, R.color.colorBlockDefault);
        ifNeedReset = false;
    }


    public void reset(Context c) {
        init(c);
    }
}
