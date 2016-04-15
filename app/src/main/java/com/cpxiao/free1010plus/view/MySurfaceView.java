//package com.cpxiao.free1010plus.view;
//
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
///**
// * Created by cpxiao on 16/01/25.
// * MySurfaceView
// */
//public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
//
//	/**
//	 * SurfaceHolder用于控制SurfaceView的大小、格式等，用于监听SurfaceView的状态。
//	 */
//	private SurfaceHolder mSurfaceHolder;
//	private Paint mPaint;
//
//	/**
//	 * 初始化文本坐标
//	 */
//	private int textX = 100;
//	private int textY = 100;
//
//	/**
//	 * 声明一个线程
//	 */
//	private Thread mThread;
//
//	/**
//	 * 线程消亡的标志位
//	 */
//	private boolean flag = false;
//
//	/**
//	 * 声明一个画布
//	 */
//	private Canvas mCanvas;
//
//	/**
//	 * 声明屏幕的宽高,获取视图的宽高一定要在视图创建之后才可获取，即surfaceCreated之后获取，否则一直为0
//	 */
//	private int screenWidth, screenHeight;
//
//	public MySurfaceView(Context context) {
//		super(context);
//		init();
//	}
//
//	public MySurfaceView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init();
//	}
//
//	public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		init();
//	}
//
//	private void init() {
//		/** 实例SurfaceHolder*/
//		mSurfaceHolder = getHolder();
//
//		/**为SurfaceView添加状态监听*/
//		mSurfaceHolder.addCallback(this);
//
//		/**实例一个画笔*/
//		mPaint = new Paint();
//		mPaint.setColor(Color.RED);
//		mPaint.setTextSize(30);
//
//		/**设置焦点*/
//		setFocusable(true);
//	}
//
//	/**
//	 * 重写SurfaceHolder.Callback接口的三个方法surfaceCreated()、surfaceChanged()、surfaceDestroyed()
//	 */
//
//	/**
//	 * 当SurfaceView被创建完成后响应的方法
//	 */
//	@Override
//	public void surfaceCreated(SurfaceHolder surfaceHolder) {
//		screenWidth = getWidth();
//		screenHeight = getHeight();
//		Log.d("CPXIAO", "screenWidth = " + screenWidth);
//		Log.d("CPXIAO", "screenHeight = " + screenHeight);
//		flag = true;
//		/**实例线程*/
//		mThread = new Thread(this);
//		mThread.start();
//	}
//
//	/**
//	 * 当SurfaceView状态发生改变时响应的方法
//	 */
//	@Override
//	public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//	}
//
//	/**
//	 * 当SurfaceView状态Destroyed时响应的方法
//	 */
//	@Override
//	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//		flag = false;
//	}
//
//
//	/**
//	 * 自定义绘图方法
//	 */
//	private void myDraw() {
//		try {
//			/**使用SurfaceHolder.lockCanvas()获取SurfaceView的Canvas对象，并对画布加锁.*/
//			mCanvas = mSurfaceHolder.lockCanvas();
//			/**得到自定义大小的画布，因为局部绘制，效率更高*/
//			//      Canvas canvas = mSurfaceHolder.lockCanvas(new Rect(0,0,200,200));
//
//			if (mCanvas != null) {
//				/** 在绘制之前需要将画布清空，否则画布上会显示之前绘制的内容,以下三种方法效果一致*/
//				mCanvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
//				mCanvas.drawColor(Color.WHITE);
//				mCanvas.drawRGB(255, 255, 255);
//
//				/**通过在Canvas上绘制内容来修改SurfaceView中的数据*/
//				mCanvas.drawText("mySurfaceView", textX, textY, mPaint);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (mSurfaceHolder != null) {
//				/**用于解锁画布和提交*/
//				mSurfaceHolder.unlockCanvasAndPost(mCanvas);
//			}
//		}
//	}
//
//	/**
//	 * 重写触屏监听方法
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		textX = (int) event.getX();
//		textY = (int) event.getY();
//		myDraw();
//		return true;
//	}
//
//	private int moveX = 10;
//	private int moveY = 10;
//
//	/**
//	 * 程序逻辑代码
//	 */
//	private void logic() {
//		if (textX < 0) {
//			moveX = 10;
//		} else if (textX > screenWidth) {
//			moveX = -10;
//		}
//
//		if (textY < 0) {
//			moveY = 10;
//		} else if (textY > screenHeight) {
//			moveY = -10;
//		}
//
//		textX += moveX;
//		textY += moveY;
//
//	}
//
//	/**
//	 * 设置刷新时间为50毫秒
//	 */
//	private static final int REFRESH_TIME = 50;
//
//	@Override
//	public void run() {
//		while (flag) {
//			long start = System.currentTimeMillis();
//			myDraw();
//			logic();
//			long end = System.currentTimeMillis();
//			try {
//				long use_time = end - start;
//				if (use_time < REFRESH_TIME) {
//					Thread.sleep(REFRESH_TIME - use_time);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
