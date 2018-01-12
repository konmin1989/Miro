package com.konmin.miro.internal.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.icu.util.Measure;
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


    public static final int SIZE = 32;
    private static final float STROKE_WIDTH = 1;
    private float mDensity;
    private float mWidth;

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private Paint mPathPaint;
    private boolean mChecked = false;
    private Rect mRect;
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
        mRect = new Rect();
        initStrokePaint();
        initFillPaint();
        //initCheckPath();
        initPathPaint();
    }


    private void initStrokePaint() {
        mStrokePaint = new Paint();
        mStrokePaint.setColor(Color.WHITE);
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
        mPathPaint.setStrokeWidth(3 * mDensity);
        mPathPaint.setAntiAlias(true);
    }

    private void initCheckPath() {
        mCheckPath = new Path();
        float width = mWidth - getPaddingLeft() - getPaddingRight();
        mCheckPath.moveTo(width / 7, width / 2);
        mCheckPath.lineTo(width / 2.5f, width - width / 4);
        mCheckPath.lineTo(width - width / 6, width / 5);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeSpec = MeasureSpec.makeMeasureSpec((int) (SIZE * mDensity), MeasureSpec.EXACTLY);
        super.onMeasure(sizeSpec, sizeSpec);
    }


    private int onMeasureWidth(int widthMeasureSpec) {
        int sizeSpec = MeasureSpec.makeMeasureSpec((int) (SIZE * mDensity), MeasureSpec.EXACTLY);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                sizeSpec = size;
                break;
            case MeasureSpec.AT_MOST:
                sizeSpec = Math.min(sizeSpec, size);
                break;
            case MeasureSpec.UNSPECIFIED:
                sizeSpec = size;
                break;
        }
        return sizeSpec;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.set(getPaddingLeft(), getPaddingTop(), (int) mWidth - getPaddingRight(), (int) mWidth - getPaddingRight());
        if (mChecked) {
            canvas.drawRect(mRect, mFillPaint);
            if (mCheckPath == null) {
                initCheckPath();
            }
            canvas.translate(getPaddingLeft(), getPaddingTop());
            canvas.drawPath(mCheckPath, mPathPaint);
        } else {
            canvas.drawRect(mRect, mStrokePaint);
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
