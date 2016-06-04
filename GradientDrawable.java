package com.GradientDrawable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Path.Direction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GradientDrawable extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(new SampleView(this));
    }
    
    class SampleView extends View{
    	private Path mPath = new Path();
    	private Matrix matrix = new Matrix();
    	private Bitmap bitmap;
    	//放大镜的半径
    	private static final int RADIUS = 80;
    	//放大倍数
    	private static final int FACTOR = 2;
    	private int mCurrentX, mCurrentY;

    	public SampleView(Context context) {
    		super(context);
    		mPath.addCircle(RADIUS, RADIUS, RADIUS, Direction.CW);
    		matrix.setScale(FACTOR, FACTOR);
    		
    		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
    	}	
    	
    	@Override
    	public boolean onTouchEvent(MotionEvent event) {
    		mCurrentX = (int) event.getX();
    		mCurrentY = (int) event.getY();
    		
    		invalidate();
    		return true;
    	}
    	
    	@Override
    	public void onDraw(Canvas canvas) {
    		super.onDraw(canvas);
    		//底图
    		canvas.drawBitmap(bitmap, 0, 0, null);
    		//剪切
    		canvas.translate(mCurrentX - RADIUS, mCurrentY - RADIUS);
    		canvas.clipPath(mPath);	
    		//画放大后的图
    		canvas.translate(RADIUS-mCurrentX*FACTOR, RADIUS-mCurrentY*FACTOR);
    		canvas.drawBitmap(bitmap, matrix, null);		
    	}
    }
}