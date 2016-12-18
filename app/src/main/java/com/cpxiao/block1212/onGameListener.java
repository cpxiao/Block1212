package com.cpxiao.block1212;

/**
 * @author cpxiao on 2015/10/22.
 */
public interface onGameListener {
    /**
     * 统计分数
     */
    void onScoreChange(int score, int bestScore);


    /**
     * 游戏结束
     */
    void onGameOver();


}
