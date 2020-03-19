package com.fatdoge.loadingview.alarm;

import android.content.Context;
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
 * Created on 2020/3/8.
 */
public class CircleProgressView extends View {

    private Paint mPaint;
    private int mProgress;
    private final int MAX_PROGRESS = 100;
    private final int MIN_PROGRESS = 0;
    private final int MAX_RANGE = 360;
    private final int HALF_RANGE = 180;


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mProgress = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mPaint.setColor(getResources().getColor(R.color.color_B3373636));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        if (mProgress > MAX_PROGRESS) {
            return;
        }
        float sweepAngle = MAX_RANGE * (mProgress / (MAX_PROGRESS * 1f));
        float startAngle = (HALF_RANGE - sweepAngle) / 2;
        float left = (width - height >> 1) >> 1;
        canvas.drawArc(left, 0, width - left, height,
                startAngle,sweepAngle,false,mPaint);
    }

    /**
     * 进度范围：0-100
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < MIN_PROGRESS || progress > MAX_PROGRESS) {
            return;
        }
        mProgress = progress;
        invalidate();
    }
}
