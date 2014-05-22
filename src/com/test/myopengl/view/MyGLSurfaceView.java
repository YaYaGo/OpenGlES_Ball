package com.test.myopengl.view;

import com.test.myopengl.graphic.Ball;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView{
	private MyRenderer mRenderer = null;
	private float mPreviousY;//�ϴεĴ���λ��Y����
    private float mPreviousX;//�ϴεĴ���λ��X����
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�Ƕ����ű���
	private Ball mBall = null;
	public MyGLSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setEGLContextClientVersion(2); //����ʹ��OPENGL ES2.0
		mRenderer = new MyRenderer(context);
		setRenderer(mRenderer);
		
	}

	//�����¼��ص�����
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
            float dy = y - mPreviousY;//���㴥�ر�Yλ��
            float dx = x - mPreviousX;//���㴥�ر�Xλ�� 
  
            mBall.setyAngle(mBall.getyAngle() +dx * TOUCH_SCALE_FACTOR);
            //���������Բ��y����ת�ĽǶ�
            mBall.setxAngle(mBall.getxAngle()+ dy * TOUCH_SCALE_FACTOR);
            //���������Բ��x����ת�ĽǶ�
        }
        mPreviousY = y;//��¼���ر�λ��
        mPreviousX = x;//��¼���ر�λ��
        return true;
    }
}
