package com.fatdoge.loadingview.waveball;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.fatdoge.loadingview.R;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/22.
 */
class OvalRingView extends View {

    private Context mContext;
    private Paint mPaint;
    private float mOvalRingOuterMaxRadius;
    private float mOvalRingInnerRadius;
    private float mMinRingWidth;
    private float mMaxRingWidth;
    private int mOvalRingColor;

    private final static float DEFAULT_OUTER_MAX_RADIUS = 200f;
    private final static float DEFAULT_INNER_RADIUS = 180f;
    private final static float DEFAULT_MIN_RING_WIDTH = 2.5f;
    private final static float DEFAULT_MAX_RING_WIDTH = 15f;


    public OvalRingView(Context context) {
        this(context, null);
    }

    public OvalRingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPaint = new Paint();
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.OvalRingView);
        mOvalRingOuterMaxRadius = typedArray.getDimension(R.styleable.OvalRingView_ovalRingOuterMaxRadius, DEFAULT_OUTER_MAX_RADIUS);
        mOvalRingInnerRadius = typedArray.getDimension(R.styleable.OvalRingView_ovalRingInnerRadius, DEFAULT_INNER_RADIUS);
        mMinRingWidth = typedArray.getDimension(R.styleable.OvalRingView_ovalRingMinWidth, DEFAULT_MIN_RING_WIDTH);
        mMaxRingWidth = typedArray.getDimension(R.styleable.OvalRingView_ovalRingMaxWidth, DEFAULT_MAX_RING_WIDTH);
        mOvalRingColor = typedArray.getColor(R.styleable.OvalRingView_ovalRingColor,
                mContext.getResources().getColor(R.color.color_77EBF8));
        typedArray.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int ringX = width / 2;
        int ringY = height / 2;

        mPaint.setColor(mOvalRingColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        drawOral(canvas, ringX, ringY, 0, 90, mMinRingWidth, 1);
        drawOral(canvas, ringX, ringY, 90, 180, mMaxRingWidth, -1);
        drawOral(canvas, ringX, ringY, 180, 270, mMinRingWidth, 1);
        drawOral(canvas, ringX, ringY, 270, 360, mMaxRingWidth, -1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawOral(Canvas canvas, float ringX, float ringY,
                          int startAngle, int endAngle, float startRingWidth, int posOrNe) {
        float radiusAngle = (mMaxRingWidth - mMinRingWidth) / 90;
        for (int angle = startAngle; angle <= endAngle; ++ angle) {
            float width = radiusAngle * (angle - startAngle) * posOrNe + startRingWidth;
            float radius = mOvalRingInnerRadius + width / 2;
            mPaint.setStrokeWidth(width);
            canvas.drawArc(ringX - radius, ringY - radius,
                    ringX + radius, ringY + radius,
                    angle, 1, false,  mPaint);
        }
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
            size = (int) Math.ceil (mOvalRingOuterMaxRadius * 2);
        }
        return size;
    }

    public void setOvalRingOuterMaxRadius(float ovalRingOuterMaxRadius) {
        this.mOvalRingOuterMaxRadius = ovalRingOuterMaxRadius;
        requestLayout();
        invalidate();
    }

    public void setOvalRingInnerRadius(float ovalRingInnerRadius) {
        this.mOvalRingInnerRadius = ovalRingInnerRadius;
        invalidate();
    }

    public void setMinRingWidth(float minRingWidth) {
        this.mMinRingWidth = minRingWidth;
        invalidate();
    }

    public void setMaxRingWidth(float maxRingWidth) {
        this.mMaxRingWidth = maxRingWidth;
        invalidate();
    }

    public void setOvalRingColor(int ovalRingColor) {
        this.mOvalRingColor = ovalRingColor;
        invalidate();
    }
}
