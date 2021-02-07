package com.example.androidpaint;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyDraw extends View{
    float x = 500, y = 500;
    float r = 300;
    float d_x = 0, d_y = 0;
    float touch_x = 0, touch_y = 0;
    boolean is_touched = false;
    MainActivity mainActivity;
    public MyDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mainActivity = (MainActivity) context;
    }

    protected void onDraw(Canvas canvas) {
        is_touched = false;
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        if (x == canvas.getWidth() || x == 0 || y == canvas.getWidth() || y == 0) {
            if (x == canvas.getWidth() || x == 0) {
                float sw_d_x = d_x;
                d_x -= 2 * sw_d_x;
            }
            if (y == canvas.getWidth() || y == 0) {
                float sw_d_y = d_y;
                d_y -= 2 * sw_d_y;
            }
        }
        x += d_x;
        y += d_y;
        canvas.drawCircle(x, y, r, paint);
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.touch_x = event.getX();
        this.touch_y = event.getY();
        float k = (touch_x - x) / (touch_y - y);
        if (touch_y - y > 0) {
            d_y = (float) 0.5;
        } else {
            d_y = (float) -0.5;
        }
        d_x = d_y * k;
        is_touched = true;
        return false;
    }
}
