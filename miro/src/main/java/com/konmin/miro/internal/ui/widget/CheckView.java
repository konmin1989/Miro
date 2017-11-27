package com.konmin.miro.internal.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/24
 */

public class CheckView extends View {


    public static final int SIZE = 24;
    private static final float STROKE_WIDTH = 4;
    private float mDensity;
    private float mWidth;

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private Paint mPathPaint;
    private boolean mChecked = false;

    private Path mCheckPath;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDensity = getContext().getResources().getDisplayMetrics().density;
        mWidth = SIZE * mDensity;
        initStrokePaint();
        initFillPaint();
        initCheckPath();
        initPathPaint();
    }


    private void initStrokePaint() {
        mStrokePaint = new Paint();
        mStrokePaint.setColor(Color.GREEN);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mStrokePaint.setStrokeWidth(STROKE_WIDTH * mDensity);
    }


    private void initFillPaint() {
        mFillPaint = new Paint();
        mFillPaint.setColor(Color.GREEN);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setAntiAlias(true);
    }


    private void initPathPaint() {
        mPathPaint = new Paint();
        mPathPaint.setColor(Color.WHITE);
        mPathPaint.setStyle(Paint.Style.STROKE);
        //mStrokePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mPathPaint.setStrokeWidth(3 * mDensity);
        mPathPaint.setAntiAlias(true);
    }

    private void initCheckPath() {
        mCheckPath = new Path();
        mCheckPath.moveTo(mWidth / 7, mWidth / 2);
        mCheckPath.lineTo(mWidth / 2.5f, mWidth - mWidth / 4);
        mCheckPath.lineTo(mWidth - mWidth / 6, mWidth / 5);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeSpec = MeasureSpec.makeMeasureSpec((int) (SIZE * mDensity), MeasureSpec.EXACTLY);
        super.onMeasure(sizeSpec, sizeSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChecked) {
            canvas.drawRect(0, 0, mWidth, mWidth, mFillPaint);
            canvas.drawPath(mCheckPath, mPathPaint);
        } else {
            canvas.drawRect(0, 0, mWidth, mWidth, mStrokePaint);
        }
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        invalidate();
    }

    public boolean getCheck() {
        return mChecked;
    }

}
