package com.cpxiao.block1212.mode.extra;

import java.util.Random;

/**
 * @author cpxiao on 2015/10/19.
 */
public final class BaseBlockData {
    public static final int ROW_NUM = 5;
    private static final Random random = new Random();

    private BaseBlockData() {
    }

    private static final int[][] AA1 = {
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA2 = {
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA3 = {
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA4 = {
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA5 = {
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA6 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}};
    private static final int[][] AA7 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA8 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AA9 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] AB1 = {
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AB2 = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AB3 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] AC1 = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AC2 = {
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AD1 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AD2 = {
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AD3 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] AD4 = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] _3A1 = {
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _3A2 = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _3A3 = {
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _3A4 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][] _4A1 = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4A2 = {
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4A3 = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4A4 = {
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4B1 = {
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4B2 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4B3 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4B4 = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4C1 = {
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4C2 = {
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4C3 = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4C4 = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4D1 = {
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4D2 = {
            {0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4D3 = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _4D4 = {
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][] _5A1 = {
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5A2 = {
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5A3 = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5A4 = {
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5B1 = {
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5B2 = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5B3 = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5B4 = {
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5B5 = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] _5C1 = {
            {1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5C2 = {
            {1, 1, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5C3 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5C4 = {
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private static final int[][] _5D1 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5D2 = {
            {0, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5D3 = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _5D4 = {
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][] _7A1 = {
            {1, 0, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7A2 = {
            {1, 1, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7A3 = {
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7A4 = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7A5 = {
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7A6 = {
            {1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7B1 = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7B2 = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7B3 = {
            {0, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private static final int[][] _7B4 = {
            {1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};


    private static final int[][][] BASE_DATA_EASY = {
            AA1, AA2, AA3, AA4, AA5, AA6, AA7, AA8, AA9,
            _3A1, _3A2, _3A3, _3A4,
            _4A1, _4A2, _4A3, _4A4, _4B1, _4B2, _4B3, _4B4,
            _5A1, _5A2, _5A3, _5A4
    };
    private static final int[][][] BASE_DATA_NORMAL = {
            AA1, AA2, AA3, AA4, AA5, AA6, AA7, AA8, AA9, AB1, AB2, AB3,
            _3A1, _3A2, _3A3, _3A4,
            _4A1, _4A2, _4A3, _4A4, _4B1, _4B2, _4B3, _4B4, _4C1, _4C2, _4C3, _4C4,
            _5A1, _5A2, _5A3, _5A4, _5B1, _5B2, _5B3, _5B4, _5B5
    };
    private static final int[][][] BASE_DATA_HARD = {
            AA1, AA2, AA3, AA4, AA5, AA6, AA7, AA8, AA9, AB1, AB2, AB3, AC1, AC2,
            _3A1, _3A2, _3A3, _3A4,
            _4A1, _4A2, _4A3, _4A4, _4B1, _4B2, _4B3, _4B4, _4C1, _4C2, _4C3, _4C4, _4D1, _4D2, _4D3, _4D4,
            _5A1, _5A2, _5A3, _5A4, _5B1, _5B2, _5B3, _5B4, _5B5, _5C1, _5C2, _5C3
    };
    private static final int[][][] BASE_DATA_INSANE = {
            AA1, AA2, AA3, AA4, AA5, AA6, AA7, AA8, AA9, AB1, AB2, AB3, AC1, AC2, AD1, AD2, AD3, AD4,
            _3A1, _3A2, _3A3, _3A4,
            _4A1, _4A2, _4A3, _4A4, _4B1, _4B2, _4B3, _4B4, _4C1, _4C2, _4C3, _4C4, _4D1, _4D2, _4D3, _4D4,
            _5A1, _5A2, _5A3, _5A4, _5B1, _5B2, _5B3, _5B4, _5B5, _5C1, _5C2, _5C3, _5C4, _5D1, _5D2, _5D3, _5D4,
            _7A1, _7A2, _7A3, _7A4, _7A5, _7A6, _7B1, _7B2, _7B3, _7B4
    };

    public static int[][] getBaseData(int gameDifficulty) {
        if (gameDifficulty == GameDifficulty.EASY) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_EASY.length);
            return BaseBlockData.BASE_DATA_EASY[tmp];
        } else if (gameDifficulty == GameDifficulty.NORMAL) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_NORMAL.length);
            return BaseBlockData.BASE_DATA_NORMAL[tmp];
        } else if (gameDifficulty == GameDifficulty.HARD) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_HARD.length);
            return BaseBlockData.BASE_DATA_HARD[tmp];
        } else if (gameDifficulty == GameDifficulty.INSANE) {
            int tmp = random.nextInt(BaseBlockData.BASE_DATA_INSANE.length);
            return BaseBlockData.BASE_DATA_INSANE[tmp];
        }
        return null;
    }


}
