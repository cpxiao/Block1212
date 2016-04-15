package com.cpxiao.free1010plus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cpxiao.free1010plus.R;
import com.cpxiao.free1010plus.data.BaseBlockData;
import com.cpxiao.free1010plus.data.Block;
import com.cpxiao.free1010plus.onGameListener;
import com.cpxiao.free1010plus.utils.Extra;
import com.cpxiao.free1010plus.utils.SharedPreferencesUtils;
import com.cpxiao.free1010plus.utils.SharedPreferencesUtils2;


/**
 * @author cpxiao on 15/10/19.
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private static final String TAG = "CPXIAO";
    private Context mContext;

    private static final int GAME_TYPE_DEFAULT = 10;
    private static int mGameType = GAME_TYPE_DEFAULT;
    private float paddingTop = 0, paddingLeft = 0;
    private float blockSize;

    /**
     * 分数
     */
    private int mScore = 0, mBestScore = 0;
    private onGameListener mGameListener;


    /**
     * 待选图案
     */
    private static final float baseBlockPercentageSmall = 0.7f;
    private static final float baseBlockPercentageBig = 0.9f;
    private BaseBlockData[] mBaseBlockDataArray;
    private int mBaseBlockNumber;
    private int mBaseBlockChecked = -1;

    private int baseBlockSizeSmall;
    private int baseBlockSizeBig;


    private Block[][] mBlockStore;

    //SurfaceHolder用于控制SurfaceView的大小、格式等，用于监听SurfaceView的状态。
    private SurfaceHolder mSurfaceHolder;
    private Paint mBlockPaint;

    //声明一个线程
    //	private static Thread mThread;
    //线程消亡的标志位
    private boolean thread_over_flag = true;

    //声明一个画布
    private Canvas mCanvas;

    public GameSurfaceView(Context context, int gameType, boolean isNewGame) {
        super(context);
        mContext = context;
        mGameType = gameType;
        init(context, isNewGame);
    }

    public GameSurfaceView(Context context) {
        super(context);
        init(context, true);
    }

    private void init(Context c, boolean isNewGame) {
        //实例SurfaceHolder
        mSurfaceHolder = getHolder();
        //为SurfaceView添加状态监听
        mSurfaceHolder.addCallback(this);

        //实例化画笔并打开抗锯齿
        mBlockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置阴影效果
//        mBlockPaint.setShadowLayer(10, 3, 3, Color.DKGRAY);

        mScore = 0;
        mBestScore = SharedPreferencesUtils.getScore(c);

        String sudoku = (String) SharedPreferencesUtils2.getParam(getContext(), Extra.KEY_CONTINUE_BLOCKS, "");
        if (!isNewGame) {
            mScore = (int) SharedPreferencesUtils2.getParam(getContext(), Extra.KEY_CONTINUE_SCORE, 0);
            initBlocks(sudoku);
        }
        //若读取进度失败，开始新游戏
        if (mBlockStore == null) {
            mScore = 0;
            initBlocks();
        }
        initBaseBlock();
    }

    private void initBlocks(String data) {
        if (data == null) {
            return;
        }
        String[] tmp = data.split(";");
        if (tmp.length != mGameType * mGameType) {
            return;
        }

        mBlockStore = new Block[mGameType][mGameType];
        int x, y;
        for (int i = 0; i < tmp.length; i++) {
            x = i % mGameType;
            y = i / mGameType;
            Block tmpBlock = new Block(getContext());
            String[] tmp2 = tmp[i].split(",");
            if (tmp2.length != 2) {
                mBlockStore = null;
                return;
            }
            tmpBlock.mData = Integer.valueOf(tmp2[0]);
            tmpBlock.mColor = Integer.valueOf(tmp2[1]);

            mBlockStore[y][x] = tmpBlock;
        }
    }

    public void setOnGameListener(onGameListener listener) {
        mGameListener = listener;
    }

    public void save() {
        saveScore();
        saveBlocks();
    }

    private void saveScore() {
        SharedPreferencesUtils2.setParam(getContext(), Extra.KEY_CONTINUE_SCORE, mScore);
    }

    private void saveBlocks() {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < mGameType; i++) {
            for (int j = 0; j < mGameType; j++) {
                if (i == mGameType && j == mGameType) {
                    tmp.append(mBlockStore[i][j].mData).append(",").append(mBlockStore[i][j].mColor);
                } else {
                    tmp.append(mBlockStore[i][j].mData).append(",").append(mBlockStore[i][j].mColor).append(";");
                }
            }
        }
        SharedPreferencesUtils2.setParam(getContext(), Extra.KEY_CONTINUE_BLOCKS, tmp.toString());
    }


    private void initBlocks() {
        mBlockStore = new Block[mGameType][mGameType];
        for (int i = 0; i < mGameType; i++) {
            for (int j = 0; j < mGameType; j++) {
                mBlockStore[j][i] = new Block(mContext);
            }
        }
    }

    private void initBaseBlock() {
        mBaseBlockNumber = mGameType / BaseBlockData.ROW_NUM;
        mBaseBlockDataArray = new BaseBlockData[mBaseBlockNumber];
        for (int i = 0; i < mBaseBlockNumber; i++) {
            mBaseBlockDataArray[i] = new BaseBlockData(mContext);
        }
    }

    public void restart(Context c) {
        init(c, true);
        thread_over_flag = false;
        myDraw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        int blockWidth = screenWidth / (mGameType + 2);
        int blockHeight = screenHeight / (mGameType + 4);

        if (mGameType == 0) {
            mGameType = GAME_TYPE_DEFAULT;
        }

        blockSize = Math.min(blockWidth, blockHeight);
        baseBlockSizeSmall = (int) (blockSize * baseBlockPercentageSmall);
        baseBlockSizeBig = (int) (blockSize * baseBlockPercentageBig);

        paddingTop = (screenHeight - mGameType * blockSize - BaseBlockData.ROW_NUM * baseBlockSizeSmall) / 2;
        paddingLeft = (screenWidth - mGameType * blockSize) / 2;

        myDraw();

        //实例线程
        thread_over_flag = false;
        //		mThread = new Thread(this);
        //		mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread_over_flag = true;
    }

    private void myDraw() {
        try {
            //使用SurfaceHolder.lockCanvas()获取SurfaceView的Canvas对象，并对画布加锁.
            mCanvas = mSurfaceHolder.lockCanvas();
            //得到自定义大小的画布，因为局部绘制，效率更高
            //		Canvas mCanvas = mSurfaceHolder.lockCanvas(new Rect(x - 400, y - 400, x + 400, y + 400));

            /**
             * 在绘制之前需要将画布清空
             */
            mCanvas.drawColor(ContextCompat.getColor(mContext, R.color.colorBackgroundDefault));


            drawBlocks(mCanvas);
            drawAllBaseBlock(mCanvas, mBaseBlockDataArray);

            //通过在Canvas上绘制内容来修改SurfaceView中的数据
            //		mCanvas.drawText("mySurfaceView", textX, textY, mLinePaint);
            //用于解锁画布和提交
            //			mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mSurfaceHolder != null && mCanvas != null) {
                //用于解锁画布和提交
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }

    /**
     * 绘制圆角正方形
     *
     * @param canvas         canvas
     * @param x              正方形中心x坐标
     * @param y              正方形中心y坐标
     * @param halfSideLength 正方形边长的一半
     * @param paint          paint
     */
    private void drawRoundSquare(Canvas canvas, float x, float y, float halfSideLength, Paint paint) {
        halfSideLength -= halfSideLength / 10;
        RectF rectF = new RectF(x - halfSideLength, y - halfSideLength, x + halfSideLength, y + halfSideLength);
        canvas.drawRoundRect(rectF, halfSideLength / 2.5f, halfSideLength / 2.5f, paint);
    }

    /**
     * 绘制游戏区域方块
     *
     * @param canvas canvas
     */
    private void drawBlocks(Canvas canvas) {
        for (int i = 0; i < mGameType; i++) {
            for (int j = 0; j < mGameType; j++) {
                Block block = mBlockStore[i][j];
                mBlockPaint.setColor(block.mColor);
                if (block.isTempColor) {
                    mBlockPaint.setAlpha(160);
                } else {
                    mBlockPaint.setAlpha(255);
                }
                drawRoundSquare(canvas, paddingLeft + (j + 0.5f) * blockSize, paddingTop + (i + 0.5f) * blockSize,
                        blockSize / 2, mBlockPaint);
            }
        }
    }

    /**
     * 绘制游戏待选方块
     *
     * @param canvas canvas
     * @param data   data
     */
    private void drawAllBaseBlock(Canvas canvas, BaseBlockData[] data) {
        drawBaseBlock(canvas, data, false);
        drawBaseBlock(canvas, data, true);

    }

    /**
     * 绘制游戏待选方块
     *
     * @param canvas    canvas
     * @param data      data
     * @param isChecked isChecked
     */
    private void drawBaseBlock(Canvas canvas, BaseBlockData[] data, boolean isChecked) {
        if (canvas == null || data == null || data.length < mBaseBlockNumber) {
            return;
        }
        for (int i = 0; i < mBaseBlockNumber; i++) {
            float x = paddingLeft + i * blockSize * BaseBlockData.ROW_NUM;
            float y = paddingTop + baseBlockSizeSmall + mGameType * blockSize;

            if (isChecked && i == mBaseBlockChecked) {
                drawBaseBlock(canvas, eventX - differentX, eventY - differentY, data[i],
                        baseBlockSizeBig, true);
            } else if (!isChecked && i != mBaseBlockChecked) {
                drawBaseBlock(canvas, x, y, data[i], baseBlockSizeSmall, false);
            }
        }
    }


    private void drawBaseBlock(Canvas canvas, float x, float y, BaseBlockData baseBlockData, int baseBlockSize,
                               boolean isChecked) {
        mBlockPaint.setColor(baseBlockData.mColor);

        for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
            for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                if (baseBlockData.baseData[i][j] == 1) {
                    if (isChecked) {
                        drawRoundSquare(canvas, x + (j + 0.5f) * blockSize, y + (i + 0.5f) *
                                blockSize, baseBlockSize / 2, mBlockPaint);
                    } else {
                        drawRoundSquare(canvas, x + (j + 0.5f) * baseBlockSizeSmall, y + (i + 0.5f) *
                                baseBlockSizeSmall, baseBlockSize / 2, mBlockPaint);
                    }
                }
            }
        }
    }

    /**
     * 当前点击坐标位置
     */
    float eventX, eventY;
    /**
     * 当前点击位置与baseBlock左上角的差值
     */
    float differentX, differentY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (thread_over_flag) {
//            if (isGameOver()) {
//                if (mGameListener != null) {
//                    mGameListener.onGameOver();
//                }
//            }
            return true;
        }
        eventX = event.getX();
        eventY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            float minY = (paddingTop + baseBlockSizeSmall + mGameType * blockSize);
            float maxY = (paddingTop + baseBlockSizeSmall + mGameType * blockSize
                    + baseBlockSizeSmall * BaseBlockData.ROW_NUM);
            if (eventY >= minY && eventY <= maxY) {
                for (int i = 0; i < mBaseBlockNumber; i++) {
                    float minX = paddingLeft + i * blockSize * BaseBlockData.ROW_NUM;
                    float maxX = paddingLeft + i * blockSize * BaseBlockData.ROW_NUM + baseBlockSizeSmall *
                            BaseBlockData.ROW_NUM;
                    if (eventX >= minX && eventX <= maxX) {
                        mBaseBlockChecked = i;
                        differentX = (int) (eventX - minX);
                        differentY = blockSize * (mBaseBlockDataArray[i].sizeHeight + 1f);
                    }
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mBaseBlockChecked >= 0) {
                int numX = (int) ((eventX - differentX - paddingLeft + blockSize * 0.5) / blockSize);
                int numY = (int) ((eventY - differentY - paddingTop + blockSize * 0.5) / blockSize);

                if (isCanBePlace(numX, numY, mBaseBlockDataArray[mBaseBlockChecked])) {
                    //放置成功，更新数据
                    mScore += mBaseBlockDataArray[mBaseBlockChecked].baseScore;
                    updateBlocks(numX, numY, true);
                    //更新待选块数据
                    updateBaseBlock();
                    mBaseBlockChecked = -1;
                } else {
                    //baseBlock回归原处
                    mBaseBlockChecked = -1;
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (mBaseBlockChecked >= 0) {
                int numX = (int) ((eventX - differentX - paddingLeft + blockSize * 0.5) / blockSize);
                int numY = (int) ((eventY - differentY - paddingTop + blockSize * 0.5) / blockSize);

                if (isCanBePlace(numX, numY, mBaseBlockDataArray[mBaseBlockChecked])) {
                    //放置成功，更新数据
                    updateBlocks(numX, numY, false);
                } else {
                    clearTempColor();
                }
            }
        }


        logic();
        myDraw();
        //判断游戏是否结束
        if (isGameOver()) {
            Log.d("CPXIAO", "game over!!!!!");
            thread_over_flag = true;
            if (mGameListener != null) {
                mGameListener.onGameOver();
            }
        }
        return true;
    }


    private boolean updateBlocks(int x, int y, boolean isSave) {
        clearTempColor();
        for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
            for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                if (mBaseBlockDataArray[mBaseBlockChecked].baseData[i][j] > 0) {
                    if (isSave) {
                        Block block = mBlockStore[i + y][j + x];
                        block.mData = 1;
                        block.mColor = mBaseBlockDataArray[mBaseBlockChecked].mColor;
                    } else {
                        Block block = mBlockStore[i + y][j + x];
                        block.mColor = mBaseBlockDataArray[mBaseBlockChecked].mColor;
                        block.isTempColor = true;
                    }
                }
            }
        }
        return true;
    }

    private void clearTempColor() {
        for (int y = 0; y < mBlockStore.length; y++) {
            for (int x = 0; x < mBlockStore[0].length; x++) {
                Block block = mBlockStore[y][x];
                if (block.isTempColor) {
                    block.isTempColor = false;
                    block.reset(getContext().getApplicationContext());
                }
            }
        }
    }

    private void updateBaseBlock() {
        if (mBaseBlockChecked >= 0) {
            mBaseBlockDataArray[mBaseBlockChecked] = new BaseBlockData(mContext);
        }
    }


    private void logic() {

        //判断横排
        int line_num_h = 0;
        for (int i = 0; i < mGameType; i++) {
            int count = 0;
            for (int j = 0; j < mGameType; j++) {
                if (mBlockStore[j][i].mData > 0) {
                    count++;
                }
            }
            if (count == mGameType) {
                line_num_h++;
                for (int k = 0; k < mGameType; k++) {
                    mBlockStore[k][i].ifNeedReset = true;
                }
            }
        }

        //判断竖排
        int line_num_v = 0;
        for (int i = 0; i < mGameType; i++) {
            int count = 0;
            for (int j = 0; j < mGameType; j++) {
                if (mBlockStore[i][j].mData > 0) {
                    count++;
                }
            }
            if (count == mGameType) {
                line_num_v++;
                for (int k = 0; k < mGameType; k++) {
                    mBlockStore[i][k].ifNeedReset = true;
                }
            }
        }

        //更新数据
        for (int i = 0; i < mGameType; i++) {
            for (int j = 0; j < mGameType; j++) {
                if (mBlockStore[i][j].ifNeedReset) {
                    mBlockStore[i][j].reset(mContext);
                }
            }
        }

        int line_num_sum = line_num_h + line_num_v;
        if (line_num_sum > 0)
            Log.d("CPXIAO", "sum = " + line_num_sum + ",h = " + line_num_h + ",v = " + line_num_v);
        mScore += mGameType * ((line_num_sum + 1) * line_num_sum / 2);
        //更新分数
        if (mGameListener != null) {
            mGameListener.onScoreChange(mScore, mBestScore);
        }
    }

    private boolean isGameOver() {
        for (int i = 0; i < mBaseBlockNumber; i++) {
            for (int x = 0; x < mGameType; x++) {
                for (int y = 0; y < mGameType; y++) {
                    if (isCanBePlace(x, y, mBaseBlockDataArray[i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isCanBePlace(int x, int y, BaseBlockData baseBlockData) {
        for (int i = 0; i < BaseBlockData.ROW_NUM; i++) {
            for (int j = 0; j < BaseBlockData.ROW_NUM; j++) {
                if (baseBlockData.baseData[i][j] > 0) {
                    if (y + i < 0 || x + j < 0) {
                        return false;
                    } else if (y + i >= mGameType || x + j >= mGameType) {
                        return false;
                    } else if (mBlockStore[y + i][x + j].mData > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //设置刷新时间为50毫秒
    private static final int REFRESH_TIME = 50;

    @Override
    public void run() {
        while (!thread_over_flag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                long use_time = end - start;
                if (use_time < REFRESH_TIME) {
                    Thread.sleep(REFRESH_TIME - use_time);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
