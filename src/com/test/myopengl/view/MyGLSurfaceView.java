package com.test.myopengl.view;

import com.test.myopengl.graphic.Ball;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView{
	private MyRenderer mRenderer = null;
	private float mPreviousY;//上次的触控位置Y坐标
    private float mPreviousX;//上次的触控位置X坐标
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//角度缩放比例
	private Ball mBall = null;
	public MyGLSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
		mRenderer = new MyRenderer(context);
		setRenderer(mRenderer);
		
	}

	//触摸事件回调方法
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        mBall = mRenderer.getBallObject();
        if(mBall==null)
        {
        	return true;
        }
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//计算触控笔Y位移
            float dx = x - mPreviousX;//计算触控笔X位移 
  
            mBall.setyAngle(mBall.getyAngle() +dx * TOUCH_SCALE_FACTOR);
            //设置填充椭圆绕y轴旋转的角度
            mBall.setxAngle(mBall.getxAngle()+ dy * TOUCH_SCALE_FACTOR);
            //设置填充椭圆绕x轴旋转的角度
        }
        mPreviousY = y;//记录触控笔位置
        mPreviousX = x;//记录触控笔位置
        return true;
    }
}
