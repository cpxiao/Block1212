package com.cpxiao.block1212.mode;

import android.content.Context;
import android.graphics.Color;

import com.cpxiao.block1212.Extra;
import com.cpxiao.block1212.R;

import java.util.Random;


/**
 * @author cpxiao on 2015/10/19.
 */
public class BaseBlock {
    public int[][] baseData;
    public int color;
    public int sizeWidth;
    public int sizeHeight;
    public int baseScore;

    public BaseBlock(Context c, int gameDifficulty) {
        this.baseData = getBaseData(gameDifficulty);
        this.color = getColor(c);
        this.sizeWidth = getSizeWidth();
        this.sizeHeight = getSizeHeight();
        this.baseScore = getBaseScore();
    }

    private int[][] getBaseData(int gameDifficulty) {
        Random random = new Random();
        if (gameDifficulty == Extra.GameDifficulty.EASY) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_EASY.length);
            return BaseBlockData.BASE_DATA_EASY[tmp];
        } else if (gameDifficulty == Extra.GameDifficulty.NORMAL) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_NORMAL.length);
            return BaseBlockData.BASE_DATA_NORMAL[tmp];
        } else if (gameDifficulty == Extra.GameDifficulty.HARD) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_HARD.length);
            return BaseBlockData.BASE_DATA_HARD[tmp];
        }
        return null;
    }


    private int getColor(Context c) {
        String[] colors = c.getResources().getStringArray(R.array.app);
        Random random = new Random();
        int i = random.nextInt(colors.length);
        return Color.parseColor(colors[i]);
    }

    private int getSizeWidth() {
        int result = 0;
        if (baseData != null) {
            for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
                for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                    if (baseData[i][j] > 0 && j >= result) {
                        result = j + 1;
                    }
                }
            }
        }
        return result;
    }

    private int getSizeHeight() {
        int result = 0;
        if (baseData != null) {
            for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
                for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                    if (baseData[i][j] > 0 && i >= result) {
                        result = i + 1;
                    }
                }
            }
        }
        return result;
    }

    private int getBaseScore() {
        int result = 0;
        if (baseData != null) {
            for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
                for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                    if (baseData[i][j] > 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

}
