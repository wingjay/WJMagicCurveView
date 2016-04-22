package com.wingjay.wjmagiccurveviewdemo;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by wingjay on 4/22/16.
 * GitHub: https://github.com/wingjay
 * Blog: https://wingjay.com
 * Weibo: http://weibo.com/1625892654
 * Mail: yinjiesh@126.com
 */
public class WJMagicCurveView extends View {

    private final float RADIUS_AX_DEFAULT = 300, RADIUS_AY_DEFAULT = 10;
    private final float RADIUS_BX_DEFAULT = 10, RADIUS_BY_DEFAULT = 300;

    private final float SPEED_OUTER_POINT_DEFAULT = 0.032f, SPEED_INNER_POINT_DEFAULT = 0.005f;

    private final int DURATION_SEC_DEFAULT = 50;
    private final int LOOP_TOTAL_COUNT_DEFAULT = 30;

    private float radiusAX = RADIUS_AX_DEFAULT, radiusAY = RADIUS_AY_DEFAULT;
    private float radiusBX = RADIUS_BX_DEFAULT, radiusBY = RADIUS_BY_DEFAULT;

    private float speedOuterPoint = SPEED_OUTER_POINT_DEFAULT, speedInnerPoint = SPEED_INNER_POINT_DEFAULT;

    private int DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 700;
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private Paint linePaint;

    float angleA = 0f, angleB = 0f;
    float aX, aY, bX, bY;

    private int backgroundColor = Color.BLACK;
    private int lineColor = Color.WHITE;
    private Paint.Style paintStyle = Paint.Style.FILL;
    private int lineAlpha = 130;
    private Bitmap bitmap;
    private Canvas c;

    private ValueAnimator valueAnimator;
    private boolean isDrawing = false;

    private int durationSec = DURATION_SEC_DEFAULT;
    private int loopTotalCount = LOOP_TOTAL_COUNT_DEFAULT;

    public WJMagicCurveView(Context context) {
        this(context, null);
    }

    public WJMagicCurveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WJMagicCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(backgroundColor);
        setupPaint();
        bitmap = Bitmap.createBitmap(DEFAULT_WIDTH, DEFAULT_HEIGHT, Bitmap.Config.ARGB_8888);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    private void setupPaint() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStyle(paintStyle);
        linePaint.setStrokeWidth(1);
        linePaint.setAlpha(lineAlpha);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            width = DEFAULT_WIDTH;
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
            height = DEFAULT_HEIGHT;
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }

    private void setupSize() {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        c = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (c == null) {
            c = new Canvas(bitmap);
            c.translate(width / 2, height / 2);
        } else {
            aX = (float) Math.cos(angleA) * radiusAX;
            aY = (float) Math.sin(angleA) * radiusAY;
            bX = (float) Math.cos(angleB) * radiusBX;
            bY = (float) Math.sin(angleB) * radiusBY;
            c.drawLine(aX, aY, bX, bY, linePaint);
        }
        canvas.drawBitmap(bitmap, 0, 0, linePaint);
    }

    /**
     * Radius for two circles
     */
    public WJMagicCurveView setRadius(float radiusAX, float radiusAY, float radiusBX, float radiusBY) {
        setDefaultRadius();
        if (radiusAX > 0) {
            this.radiusAX = radiusAX;
        }
        if (radiusAY > 0) {
            this.radiusAY = radiusAY;
        }
        if (radiusBX > 0) {
            this.radiusBX = radiusBX;
        }
        if (radiusBY > 0) {
            this.radiusBY = radiusBY;
        }
        return this;
    }

    private void setDefaultRadius() {
        this.radiusAX = RADIUS_AX_DEFAULT;
        this.radiusAY = RADIUS_AY_DEFAULT;
        this.radiusBX = RADIUS_BX_DEFAULT;
        this.radiusBY = RADIUS_BY_DEFAULT;
    }

    /**
     * set the duration time for animation.
     */
    public WJMagicCurveView setDurationSec(int durationSec) {
        if (durationSec > 0) {
            this.durationSec = durationSec;
        } else {
            this.durationSec = DURATION_SEC_DEFAULT;
        }
        return this;
    }

    /**
     * set loop count.
     */
    public WJMagicCurveView setLoopTotalCount(int loopTotalCount) {
        if (loopTotalCount > 0) {
            this.loopTotalCount = loopTotalCount;
        } else {
            this.loopTotalCount = LOOP_TOTAL_COUNT_DEFAULT;
        }
        return this;
    }

    /**
     * set speed for two circles.
     */
    public WJMagicCurveView setSpeed(int speedOuterPoint, int speedInnerPoint) {
        if (speedOuterPoint > 0) {
            this.speedOuterPoint = (float)speedOuterPoint / 1000.f;
        } else {
            this.speedOuterPoint = SPEED_OUTER_POINT_DEFAULT;
        }
        if (speedInnerPoint > 0) {
            this.speedInnerPoint = (float)speedInnerPoint / 1000.f;
        } else {
            this.speedInnerPoint = SPEED_INNER_POINT_DEFAULT;
        }
        return this;
    }

    public void startDraw() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            invalidate();
        }
        setupSize();
        Log.i("curve", "radiusAX " + radiusAX + ", radiusAY " + radiusAY
                + ", radiusBX " + radiusBX + ", radiusBY " + radiusBY
                + ", speedOuterPoint " + speedOuterPoint + ", speedInner " + speedInnerPoint
                + ", loopTotalCount " + loopTotalCount + ", duration " + durationSec);
        angleA = 0;
        angleB = 0;
        float endAngleA = loopTotalCount * (float) Math.PI;
        valueAnimator = ValueAnimator.ofFloat(0, endAngleA).setDuration(durationSec * 1000);
        valueAnimator.setEvaluator(new MyTypeEvaluator());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angleA = (float) animation.getAnimatedValue();
                angleB = (angleA / speedOuterPoint) * speedInnerPoint;
                invalidate();
            }
        });
        valueAnimator.start();
        isDrawing = true;
    }

    public void stopDraw() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
            isDrawing = false;
        }
    }

    public void destory() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    /**
     * set paint color
     */
    public void setPaintColor(int lineColor) {
        this.lineColor = lineColor;
        setupPaint();
        invalidate();
    }

    /**
     * set paint style.
     */
    private void setPaintStyle(Paint.Style style) {
        this.paintStyle = style;
        setupPaint();
        invalidate();
    }


    /**
     * set paint alpha
     */
    public void setPaintAlpha(int lineAlpha) {
        this.lineAlpha = lineAlpha;
        setupPaint();
        invalidate();
    }

    /**
     * set all eight parameters at once.
     */
    public void setAllEightParameters(float radiusAX, float radiusAY, float radiusBX, float radiusBY,
                                      int durationSec, int loopTotalCount, int speedOuterPoint, int speedInnerPoint) {
        this.setRadius(radiusAX, radiusAY, radiusBX, radiusBY)
                .setDurationSec(durationSec).setLoopTotalCount(loopTotalCount)
                .setSpeed(speedOuterPoint, speedInnerPoint);
    }

    /**
     * set a WJMagicCurveViewParameters.
     */
    public void setParameters(WJMagicCurveViewParameters wjMagicCurveViewParameters) {
        this.setAllEightParameters(wjMagicCurveViewParameters.radiusAX, wjMagicCurveViewParameters.radiusAY,
                wjMagicCurveViewParameters.radiusBX, wjMagicCurveViewParameters.radiusBY,
                wjMagicCurveViewParameters.durationSec, wjMagicCurveViewParameters.loopTotalCount,
                wjMagicCurveViewParameters.speedOuterPoint, wjMagicCurveViewParameters.speedInnerPoint);
    }

    /**
     * You can create your own parameters as below.
     */
    public enum WJMagicCurveViewParameters {
        DEFAULT(-1, -1, -1, -1, -1, -1, -1, -1),
        DIAMOND(300, 1, 300, 150, 100, -1, 60, 28),
        RING(200, 200, 300, 300, 150, -1, 60, -1),
        PYRAMID(1, 300, 300, 1, 50, -1, -1, -1),
        SHELL(1, 300, 350, 300, 10, 1, -1, -1),
        FLOWER(320, 320, 280, 280, -1, -1, -1, -1),
        LEAF(1, 300, 300, 300, 1, 10000, -1, -1),
        AIRSHIP(300, 20, 150, 150, 50, -1, -1, -1);

        float radiusAX; float radiusAY; float radiusBX; float radiusBY;
        int speedOuterPoint; int speedInnerPoint;
        int loopTotalCount;
        int durationSec;

        /**
         * if param is -1, then it means use the default value.
         * @param radiusAX X radius for A point
         * @param radiusAY Y radius for A point
         * @param radiusBX X radius for B point
         * @param radiusBY Y radius for B point
         * @param speedOuterPoint speed for A(outer) point
         * @param speedInnerPoint speed for B(inner) point
         * @param loopTotalCount whole loop count
         * @param durationSec the duration time
         */
        WJMagicCurveViewParameters(float radiusAX, float radiusAY, float radiusBX, float radiusBY,
                                   int speedOuterPoint, int speedInnerPoint,
                                   int loopTotalCount, int durationSec) {
            this.radiusAX = radiusAX;
            this.radiusAY = radiusAY;
            this.radiusBX = radiusBX;
            this.radiusBY = radiusBY;
            this.durationSec = durationSec;
            this.loopTotalCount = loopTotalCount;
            this.speedOuterPoint = speedOuterPoint;
            this.speedInnerPoint = speedInnerPoint;
        }
    }

    /**
     * No special usage, just for fun. You can edit your TypeEvaluator.
     */
    class MyTypeEvaluator implements TypeEvaluator<Float> {
        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            float currentValue = (endValue - startValue) * fraction;
            int count = (int) (currentValue / speedOuterPoint);
            return (float) count * speedOuterPoint;
        }
    }

}
