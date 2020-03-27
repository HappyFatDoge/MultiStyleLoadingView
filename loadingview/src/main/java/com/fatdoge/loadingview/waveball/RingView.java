package com.fatdoge.loadingview.waveball;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.fatdoge.loadingview.R;
import com.fatdoge.loadingview.utils.ConstantUtils;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/22.
 */
class RingView extends View {

    private Paint mPaint;
    private Context mContext;
    private float mRingWidth;
    private float mRingOuterRadius;
    private int mStartColor;
    private int mEndColor;

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        mPaint = new Paint();
    }

    private void initAttrs(AttributeSet attributeSet) {
        Resources resources = mContext.getResources();
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.RingView);
        mRingOuterRadius = typedArray.getDimension(R.styleable.RingView_ringOuterRadius,
                ConstantUtils.DEFAULT_OUTER_RADIUS);
        mRingWidth = typedArray.getDimension(R.styleable.RingView_ringWidth,
                ConstantUtils.DEFAULT_RING_WIDTH);
        mStartColor = typedArray.getColor(R.styleable.RingView_startColor,
                resources.getColor(R.color.color_3695E5));
        mEndColor = typedArray.getColor(R.styleable.RingView_endColor,
                resources.getColor(R.color.color_3ED1DD));
        typedArray.recycle();
    }

    @RequiresApi(api = android.os.Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (width < mRingOuterRadius * 2) {
            mRingOuterRadius = width / 2f;
        }
        float mRingInnerRadius = mRingOuterRadius - mRingWidth / 2;
        int ringX = width / 2;
        int ringY = height / 2;

        mPaint.setStrokeWidth(mRingWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        LinearGradient gradient = new LinearGradient(0, 0, width, height, mEndColor, mStartColor, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        canvas.drawArc(ringX - mRingInnerRadius, ringY - mRingInnerRadius,
                ringX + mRingInnerRadius, ringY + mRingInnerRadius,
                0, 360, false, mPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureSize(widthMeasureSpec, heightMeasureSpec);
    }

    private void measureSize(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measure(widthMeasureSpec);
        int height = measure(heightMeasureSpec);
        int size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }

    private int measure(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            size = (int) Math.ceil (mRingOuterRadius * 2);
        }
        return size;
    }

    public void setRingWidth(float ringWidth) {
        this.mRingWidth = ringWidth;
        invalidate();
    }

    public void setRingOuterRadius(float ringOuterRadius) {
        this.mRingOuterRadius = ringOuterRadius;
        requestLayout();
        invalidate();
    }

    public void setStartColor(int startColor) {
        this.mStartColor = startColor;
        invalidate();
    }

    public void setEndColor(int endColor) {
        this.mEndColor = endColor;
        invalidate();
    }
}
