package com.cpxiao.free1010plus;

/**
 * @author cpxiao on 15/10/22.
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
