package com.test.myopengl.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.test.myopengl.graphic.Ball;
import com.test.myopengl.graphic.Graphic;
import com.test.myopengl.graphic.Triangle;
import com.test.myopengl.utils.MatrixState;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer{
	private Ball mBall = null;
	private Context mContext = null;
	public MyRenderer(Context context)
	{
		mContext = context;
	   
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		//清除深度缓冲与颜色缓冲
        GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        //保护现场
        MatrixState.pushMatrix(); 
        //绘制球
        MatrixState.pushMatrix();
        mBall.drawSelf();
        MatrixState.popMatrix();       
        //恢复现场
        MatrixState.popMatrix();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		//设置视窗大小及位置 
    	GLES20.glViewport(0, 0, width, height); 
    	//计算GLSurfaceView的宽高比
        float ratio = (float) width / height;
        //调用此方法计算产生透视投影矩阵
        MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 1, 10);
        //调用此方法产生摄像机9参数位置矩阵
        MatrixState.setCamera(0,0,3,0f,0f,0f,0f,1.0f,0.0f);
        
        //初始化变换矩阵
        MatrixState.setInitStack();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		//设置屏幕背景色RGBA
        GLES20.glClearColor(0,0,0,1.0f);  
        
        mBall = new Ball(mContext);
        //打开深度检测
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	}

	public Ball getBallObject()
	{
		return mBall;
	}
}
