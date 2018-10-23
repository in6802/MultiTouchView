package com.cji.edu.multitouchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchView extends View {
    //변수
    final int MAX_POINTS = 10;
    float[] x = new float[MAX_POINTS];
    float[] y = new float[MAX_POINTS];
    boolean[] touching = new boolean[MAX_POINTS];
    Paint mPaint;

    public MultiTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    //그리기
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < MAX_POINTS; i++)
            if(touching[i]){
                canvas.drawCircle(x[i], y[i], 50, mPaint);
            }
    }

    // 터치
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        int action = event.getActionMasked();

        // 터치 이벤트에 따라
        switch(action){
            case MotionEvent.ACTION_DOWN: break;
            case MotionEvent.ACTION_POINTER_DOWN:
                x[id] = (int)event.getX(index);
                y[id] = (int)event.getY(index);
                touching[id] = true;
                break;
            case MotionEvent.ACTION_MOVE: break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touching[id] = false;
                break;
        }

        invalidate();
        return true;
    }
}
