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
		//�����Ȼ�������ɫ����
        GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        //�����ֳ�
        MatrixState.pushMatrix(); 
        //������
        MatrixState.pushMatrix();
        mBall.drawSelf();
        MatrixState.popMatrix();       
        //�ָ��ֳ�
        MatrixState.popMatrix();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		//�����Ӵ���С��λ�� 
    	GLES20.glViewport(0, 0, width, height); 
    	//����GLSurfaceView�Ŀ�߱�
        float ratio = (float) width / height;
        //���ô˷����������͸��ͶӰ����
        MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 1, 10);
        //���ô˷������������9����λ�þ���
        MatrixState.setCamera(0,0,3,0f,0f,0f,0f,1.0f,0.0f);
        
        //��ʼ���任����
        MatrixState.setInitStack();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		//������Ļ����ɫRGBA
        GLES20.glClearColor(0,0,0,1.0f);  
        
        mBall = new Ball(mContext);
        //����ȼ��
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	}

	public Ball getBallObject()
	{
		return mBall;
	}
}
