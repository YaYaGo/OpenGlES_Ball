package com.test.myopengl;

import com.test.myopengl.view.MyGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private MyGLSurfaceView mGLSurfaceview = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGLSurfaceview = new MyGLSurfaceView(this);
		setContentView(mGLSurfaceview);
	}

}
