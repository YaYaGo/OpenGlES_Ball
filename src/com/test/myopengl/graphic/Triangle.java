package com.test.myopengl.graphic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.test.myopengl.utils.MatrixState;
import com.test.myopengl.utils.ShaderUtil;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

//������
public class Triangle implements Graphic
{

	int mProgram;//�Զ�����Ⱦ���߳���id
    int muMVPMatrixHandle;//�ܱ任��������id
    int maPositionHandle; //����λ����������id
    int maColorHandle; //������ɫ��������id  
    String mVertexShader;//������ɫ��    	 
    String mFragmentShader;//ƬԪ��ɫ��
    static float[] mMMatrix = new float[16];//����������ƶ���ת������ת��ƽ��
	
	FloatBuffer   mVertexBuffer;//�����������ݻ���
	FloatBuffer   mColorBuffer;//������ɫ���ݻ���
    int vCount=0;	
    float xAngle=0;//��x����ת�ĽǶ�
    public Triangle(Context context)
    {    	
    	//��ʼ��������������ɫ����
    	initVertexData();
    	//��ʼ��shader
    	initShader(context);
    }
   
    public void initVertexData()
    {
    	//�����������ݵĳ�ʼ��
        vCount=3;  
        final float UNIT_SIZE=0.2f;
        float vertices[]=new float[]
        {
        	-4*UNIT_SIZE,0,
        	0,0,-4*UNIT_SIZE,
        	0,4*UNIT_SIZE,0,0
        };
		
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
        
        float colors[]=new float[]
        {
        		1,1,1,0,
        		0,0,1,0,
        		0,1,0,0
        };
        
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
    }

    //��ʼ��shader
    public void initShader(Context context)
    {
    	//���ض�����ɫ���Ľű�����
        mVertexShader=ShaderUtil.loadFromAssetsFile("vertex.sh", context.getResources());
        //����ƬԪ��ɫ���Ľű�����
        mFragmentShader=ShaderUtil.loadFromAssetsFile("frag.sh", context.getResources());  
        //���ڶ�����ɫ����ƬԪ��ɫ����������
        mProgram = ShaderUtil.createProgram(mVertexShader, mFragmentShader);
        //��ȡ�����ж���λ����������id  
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        //��ȡ�����ж�����ɫ��������id  
        maColorHandle= GLES20.glGetAttribLocation(mProgram, "aColor");
        //��ȡ�������ܱ任��������id
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");  
    }
    
    public void drawSelf()
    {        
    	 //�ƶ�ʹ��ĳ��shader����
    	 GLES20.glUseProgram(mProgram);        
    	 //��ʼ���任����
    	 MatrixState.setInitStack();
         //
         GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0); 
         //Ϊ����ָ������λ������
         GLES20.glVertexAttribPointer(
         		maPositionHandle,   
         		3, 
         		GLES20.GL_FLOAT, 
         		false,
                3*4,   
                mVertexBuffer
         );
         GLES20.glVertexAttribPointer  
         (
        		maColorHandle,
         		4,
         		GLES20.GL_FLOAT,
         		false,
                4*4,
                mColorBuffer
         );
         //������λ����������
         GLES20.glEnableVertexAttribArray(maPositionHandle);  
         GLES20.glEnableVertexAttribArray(maColorHandle);  
         //����������
         GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount); 
    }

}