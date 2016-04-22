package com.wingjay.wjmagiccurveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by android-cjj on 4/22/16.
 * GitHub: https://github.com/android-cjj
 * Mai: 929178101@qq.com
 * Weibo: http://weibo.com/chenjijun2011
 */
public class CJJArtLineView extends View {

    private Paint paint;
    private ArrayList<Float> listPos = new ArrayList<>();
    private int centerX, centerY;
    private float aX, aY, bX, bY, angleA, angleB, speedA, speedB, aXR, aYR, bXR, bYR;
    private long time;
    private String color;

    public CJJArtLineView(Context context) {
        super(context);
        init();
    }

    public CJJArtLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
    }


    public float getaX() {
        return aX;
    }

    public void setaX(float aX) {
        this.aX = aX;
    }

    public float getaY() {
        return aY;
    }

    public void setaY(float aY) {
        this.aY = aY;
    }

    public float getbX() {
        return bX;
    }

    public void setbX(float bX) {
        this.bX = bX;
    }

    public float getbY() {
        return bY;
    }

    public void setbY(float bY) {
        this.bY = bY;
    }

    public float getAngleA() {
        return angleA;
    }

    public void setAngleA(float angleA) {
        this.angleA = angleA;
    }

    public float getAngleB() {
        return angleB;
    }

    public void setAngleB(float angleB) {
        this.angleB = angleB;
    }

    public float getSpeedA() {
        return speedA;
    }

    public void setSpeedA(float speedA) {
        this.speedA = speedA;
    }

    public float getSpeedB() {
        return speedB;
    }

    public void setSpeedB(float speedB) {
        this.speedB = speedB;
    }

    public float getaXR() {
        return aXR;
    }

    public void setaXR(float aXR) {
        this.aXR = aXR;
    }

    public float getaYR() {
        return aYR;
    }

    public void setaYR(float aYR) {
        this.aYR = aYR;
    }

    public float getbXR() {
        return bXR;
    }

    public void setbXR(float bXR) {
        this.bXR = bXR;
    }

    public float getbYR() {
        return bYR;
    }

    public void setbYR(float bYR) {
        this.bYR = bYR;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#90ff7000"));
        paint.setStrokeWidth(1);


        speedA = (float) 0.015;
        speedB = (float) 0.26;
        aXR = 10;
        aYR = 100;
        bXR = 200;
        bYR = 40;
        time = 40*1000;

        startAnim(time);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        angleA += speedA;
        angleB += speedB;

        aX = (float) (Math.cos(angleA) * aXR);
        aY = (float) (Math.sin(angleA) * aYR);
        bX = (float) (Math.cos(angleB) * bXR);
        bY = (float) (Math.sin(angleB) * bYR);

        listPos.add(aX);
        listPos.add(aY);
        listPos.add(bX);
        listPos.add(bY);

        canvas.translate(centerX, centerY);
        canvas.drawColor(Color.BLACK);
        canvas.save();
        for (int i = 0; i < listPos.size(); i++) {
            if (i % 4 == 0) {
                canvas.drawLine(listPos.get(i), listPos.get(i + 1), listPos.get(i + 2), listPos.get(i + 3), paint);
            }
        }
        canvas.restore();
    }

    public void startAnim(long time) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(time);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        animator.setStartDelay(500);
        animator.start();
    }

    public void destory() {
        listPos.clear();
        invalidate();
    }

}