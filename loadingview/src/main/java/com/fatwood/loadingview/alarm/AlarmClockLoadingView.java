package com.fatwood.loadingview.alarm;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fatwood.lib_multistyleloadingview.R;
import com.fatwood.loadingview.utils.ConstantUtils;
import com.fatwood.loadingview.utils.DisplayUtils;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/8.
 */
public class AlarmClockLoadingView extends RelativeLayout {

    private Context mContext;
    private CircleProgressView mCircleProgressView;
    private ImageView mImageExpression;
    private ImageView mImageSecondHand;
    private TextView mTxtProgress;
    private TextView mTxtDescription;

    private int mTime;
    private int mProgress;
    private float mProgressTextSize;
    private int mProgressTextColor;
    private String mDescription;
    private float mDesTextSize;
    private int mDesTextColor;
    private int mCurrentType = ConstantUtils.TYPE_ERROR;

    private final int DEFAULT_TIME = 500;
    private final float DEFAULT_PROGRESS_TEXT_SIZE = 39.0f;
    private final float DEFAULT_DES_TEXT_SIZE = 33.0f;

    public AlarmClockLoadingView(Context context) throws Exception {
        this(context, null);
    }

    public AlarmClockLoadingView(Context context, AttributeSet attrs)
            throws Exception {
        this(context, attrs, 0);
    }

    public AlarmClockLoadingView(Context context, AttributeSet attrs, int defStyleAttr)
            throws Exception {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        View rootView = inflate(mContext, R.layout.view_alarm_loading_layout, this);
        initView(rootView);
    }

    private void initAttrs(AttributeSet attributeSet) {
        Resources resources = mContext.getResources();
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.AlarmClockLoadingView);
        mTime = typedArray.getInteger(R.styleable.AlarmClockLoadingView_timeForSpeed, DEFAULT_TIME);
        mProgress = typedArray.getInteger(R.styleable.AlarmClockLoadingView_progress,
                ConstantUtils.DEFAULT_PROGRESS);
        mProgressTextSize = typedArray.getDimension(R.styleable.AlarmClockLoadingView_progressTextSize,
                DEFAULT_PROGRESS_TEXT_SIZE);
        mProgressTextColor = typedArray.getColor(R.styleable.AlarmClockLoadingView_progressTextColor,
                resources.getColor(R.color.color_EDBA2D));
        mDescription = typedArray.getString(R.styleable.AlarmClockLoadingView_progressDescription);
        mDesTextSize = typedArray.getDimension(R.styleable.AlarmClockLoadingView_desTextSize,
                DEFAULT_DES_TEXT_SIZE);
        mDesTextColor = typedArray.getColor(R.styleable.AlarmClockLoadingView_desTextColor,
                resources.getColor(R.color.color_FFFFFF));
        typedArray.recycle();
    }

    private void initView(View rootView) throws Exception {
        mCircleProgressView = rootView.findViewById(R.id.circle_progress);
        mImageExpression = rootView.findViewById(R.id.image_expression);
        mImageSecondHand = rootView.findViewById(R.id.image_secondhand);
        mTxtProgress = rootView.findViewById(R.id.txt_progress);
        mTxtDescription = rootView.findViewById(R.id.txt_description);

        initProgress();
        mImageSecondHand.setPivotX(DisplayUtils.dp2px(mContext, 39.63f));
        mImageSecondHand.setPivotY(DisplayUtils.dp2px(mContext, 48.48f));
        setSecondHandSpeed(mTime);
        initDescription();
    }

    private void initProgress() throws Exception {
        Typeface typeFace= Typeface.createFromAsset(mContext.getAssets(),"huawenxingkai.ttf");
        mTxtProgress.setTypeface(typeFace);
        setProgress(mProgress);
        setProgressTextSize(TypedValue.COMPLEX_UNIT_PX, mProgressTextSize);
        setProgressTextColor(mProgressTextColor);
    }

    private void initDescription() {
        setDescription(mDescription);
        setDescriptionTextSize(TypedValue.COMPLEX_UNIT_PX, mDesTextSize);
        setDescriptionTextColor(mDesTextColor);
    }

    private void setExpression() throws Exception {
        if (mProgress < ConstantUtils.MIN_PROGRESS
                || mProgress > ConstantUtils.MAX_PROGRESS) {
            throw new Exception("Progress must be between 0-100");
        }
        if (mProgress < ConstantUtils.HALF_THRESHOLD) {
            if (mCurrentType != ConstantUtils.TYPE_SAD) {
                setExpression(R.drawable.alarm_clock_angry_expression);
                mCurrentType = ConstantUtils.TYPE_SAD;
            }
        } else if (mProgress < ConstantUtils.FOUR_FIFTHS_THRESHOLD) {
            if (mCurrentType != ConstantUtils.TYPE_ANGRY) {
                setExpression(R.drawable.alarm_clock_impatient_expression);
                mCurrentType = ConstantUtils.TYPE_ANGRY;
            }
        } else if (mCurrentType != ConstantUtils.TYPE_HAPPY){
            setExpression(R.drawable.alarm_clock_happy_expression);
            mCurrentType = ConstantUtils.TYPE_HAPPY;
        }
    }

    private void setExpression(int resource) {
        mImageExpression.setImageResource(resource);
    }

    public void setProgress(int progress) throws Exception {
        if (progress < ConstantUtils.MIN_PROGRESS
                || progress > ConstantUtils.MAX_PROGRESS) {
            throw new Exception("Progress must be between 0-100");
        }
        mProgress = progress;
        mCircleProgressView.setProgress(mProgress);
        String progressTxt = mProgress + "%";
        mTxtProgress.setText(progressTxt);
        setExpression();
    }

    public void setProgressTextSize(float textSize) {
        setProgressTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setProgressTextSize(int unit, float textSize) {
        mProgressTextSize = textSize;
        mTxtProgress.setTextSize(unit, mProgressTextSize);
    }

    public void setProgressTextColor(int color) {
        mProgressTextColor = color;
        mTxtProgress.setTextColor(mProgressTextColor);
    }

    public void setDescription(String description) {
        mDescription = description;
        mTxtDescription.setText(mDescription);
    }

    public void setDescriptionTextSize(float textSize) {
        setDescriptionTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setDescriptionTextSize(int unit, float textSize) {
        mDesTextSize = textSize;
        mTxtDescription.setTextSize(unit, mDesTextSize);
    }

    public void setDescriptionTextColor(int color) {
        mDesTextColor = color;
        mTxtDescription.setTextColor(mDesTextColor);
    }

    /**
     * 设置闹钟转动一圈时间
     * @param time
     */
    public void setSecondHandSpeed(int time) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mImageSecondHand, "rotation", 360f);
        objectAnimator.setDuration(time);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }
}