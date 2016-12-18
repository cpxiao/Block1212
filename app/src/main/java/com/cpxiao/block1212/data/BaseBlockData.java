package com.cpxiao.block1212.data;

import android.content.Context;
import android.graphics.Color;

import com.cpxiao.block1212.R;

import java.util.Random;

/**
 * @author cpxiao on 2015/10/19.
 */
public class BaseBlockData {
    public static final int ROW_NUM = 5;

    public int[][] baseData;
    public int color;
    public int sizeWidth;
    public int sizeHeight;
    public int baseScore;

    public BaseBlockData(Context c) {
        this.baseData = getBaseData();
        this.color = getColor(c);
        this.sizeWidth = getSizeWidth();
        this.sizeHeight = getSizeHeight();
        this.baseScore = getBaseScore();
    }

    private static final int[][] A1 = {
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] A2 = {
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] A3 = {
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] A4 = {
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] A5 = {
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] B1 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}};
    private static final int[][] B2 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] B3 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] B4 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] C1 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] C2 = {
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] C3 = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] C4 = {
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] D1 = {
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] D2 = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] D3 = {
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] D4 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] E1 = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] E2 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][] F1 = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] F2 = {
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] F3 = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] F4 = {
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][] G1 = {
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] G2 = {
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] G3 = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] G4 = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] H1 = {
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] H2 = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] H3 = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] H4 = {
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] H5 = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][][] BASE_DATA_EASY = {
            A1, A2, A3, A4, A5,
            B1, B2, B3, B4,
            C1, C2, C3, C4,
            D1, D2, D3, D4,
            E1, E2,
            F1, F2, F3, F4
    };


    private int[][] getBaseData() {
        Random random = new Random();
        int tmp = random.nextInt(BASE_DATA_EASY.length);
        return BASE_DATA_EASY[tmp];
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
            for (int i = 0; i < ROW_NUM; i++) {
                for (int j = 0; j < ROW_NUM; j++) {
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
            for (int i = 0; i < ROW_NUM; i++) {
                for (int j = 0; j < ROW_NUM; j++) {
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
            for (int i = 0; i < ROW_NUM; i++) {
                for (int j = 0; j < ROW_NUM; j++) {
                    if (baseData[i][j] > 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

}
