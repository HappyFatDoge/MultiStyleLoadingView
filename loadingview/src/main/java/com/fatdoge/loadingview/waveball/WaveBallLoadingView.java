package com.fatdoge.loadingview.waveball;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fatdoge.loadingview.R;
import com.fatdoge.loadingview.utils.ConstantUtils;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/25.
 */
public class WaveBallLoadingView extends RelativeLayout {

    private Context mContext;
    private RingView mRingView;
    private OvalRingView mOvalRingView;
    private TextView mTxtProgress;
    private TextView mTxtDescription;

    private float mRingWidth;
    private float mRingOuterRadius;
    private int mStartColor;
    private int mEndColor;
    private float mOvalRingOuterMaxRadius;
    private float mOvalRingInnerRadius;
    private float mMinRingWidth;
    private float mMaxRingWidth;
    private int mOvalRingColor;
    private int mProgress;
    private float mProgressTextSize;
    private int mProgressTextColor;
    private String mDescription;
    private float mDesTextSize;
    private int mDesTextColor;
    private ObjectAnimator mOvalRingObjectAnimator;

    private static final float DEFAULT_PROGRESS_TEXT_SIZE = 100.0f;
    private static final float DEFAULT_DES_TEXT_SIZE = 25.0f;

    public WaveBallLoadingView(Context context) throws Exception {
        this(context, null);
    }

    public WaveBallLoadingView(Context context, AttributeSet attrs) throws Exception {
        this(context, attrs, 0);
    }

    public WaveBallLoadingView(Context context, AttributeSet attrs, int defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        View rootView = inflate(mContext, R.layout.view_wave_ball_loading_layout, this);
        initView(rootView);
    }

    private void initAttrs(AttributeSet attributeSet) {
        Resources resources = mContext.getResources();
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.WaveBallLoadingView);
        mRingWidth = typedArray.getDimension(R.styleable.WaveBallLoadingView_ringWidth,
                ConstantUtils.DEFAULT_RING_WIDTH);
        mRingOuterRadius = typedArray.getDimension(R.styleable.WaveBallLoadingView_ringOuterRadius,
                ConstantUtils.DEFAULT_OUTER_RADIUS);
        mStartColor = typedArray.getColor(R.styleable.WaveBallLoadingView_startColor,
                resources.getColor(R.color.color_3695E5));
        mEndColor = typedArray.getColor(R.styleable.WaveBallLoadingView_endColor,
                resources.getColor(R.color.color_3ED1DD));
        mOvalRingColor = typedArray.getColor(R.styleable.WaveBallLoadingView_ovalRingColor,
                resources.getColor(R.color.color_77EBF8));
        mProgress = typedArray.getInteger(R.styleable.WaveBallLoadingView_progress,
                ConstantUtils.DEFAULT_PROGRESS);
        mProgressTextSize = typedArray.getDimension(R.styleable.WaveBallLoadingView_progressTextSize,
                DEFAULT_PROGRESS_TEXT_SIZE);
        mProgressTextColor = typedArray.getColor(R.styleable.WaveBallLoadingView_progressTextColor,
                resources.getColor(R.color.color_000000));
        mDescription = typedArray.getString(R.styleable.WaveBallLoadingView_progressDescription);
        mDesTextSize = typedArray.getDimension(R.styleable.WaveBallLoadingView_desTextSize,
                DEFAULT_DES_TEXT_SIZE);
        mDesTextColor = typedArray.getColor(R.styleable.WaveBallLoadingView_desTextColor,
                resources.getColor(R.color.color_4A4A4A));
        typedArray.recycle();

        mOvalRingOuterMaxRadius = mRingOuterRadius;
        mOvalRingInnerRadius = mRingOuterRadius - mRingWidth;
        mMinRingWidth = 0.125f * mRingWidth;
        mMaxRingWidth = 0.75f * mRingWidth;
    }

    private void initView(View rootView) throws Exception {
        mRingView = rootView.findViewById(R.id.ring_view);
        mOvalRingView = rootView.findViewById(R.id.oval_ring_view);
        mTxtProgress = rootView.findViewById(R.id.txt_progress);
        mTxtDescription = rootView.findViewById(R.id.txt_description);

        initRingView();
        initOvalRingView();
        initProgress();
        initDescription();
    }

    private void initRingView() {
        setRingWidth(mRingWidth);
        setStartColor(mStartColor);
        setEndColor(mEndColor);
        setRingOuterRadius(mRingOuterRadius);
    }

    private void initOvalRingView() {
        setOvalRingColor(mOvalRingColor);
        mOvalRingView.setMinRingWidth(mMinRingWidth);
        mOvalRingView.setMaxRingWidth(mMaxRingWidth);
        mOvalRingView.setOvalRingInnerRadius(mOvalRingInnerRadius);
        mOvalRingView.setOvalRingOuterMaxRadius(mOvalRingOuterMaxRadius);
        setOvalRingAnim();
    }

    private void setOvalRingAnim() {
        mOvalRingObjectAnimator = ObjectAnimator.ofFloat(mOvalRingView, "rotation", 360f);
        mOvalRingObjectAnimator.setDuration(500);
        mOvalRingObjectAnimator.setInterpolator(new LinearInterpolator());
        mOvalRingObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mOvalRingObjectAnimator.start();
    }

    private void initProgress() throws Exception {
        setProgress(mProgress);
        setProgressTextSize(TypedValue.COMPLEX_UNIT_PX, mProgressTextSize);
        setProgressTextColor(mProgressTextColor);
    }

    private void initDescription() {
        setDescription(mDescription);
        setDescriptionTextSize(TypedValue.COMPLEX_UNIT_PX, mDesTextSize);
        setDescriptionTextColor(mDesTextColor);
        mTxtDescription.setMaxWidth((int) (2 * (mOvalRingInnerRadius - mRingWidth)));
    }

    public void setProgress(int progress) throws Exception {
        if (progress < ConstantUtils.MIN_PROGRESS
                || progress > ConstantUtils.MAX_PROGRESS) {
            throw new Exception("Progress must be between 0-100");
        }
        mProgress = progress;
        mTxtProgress.setText(String.valueOf(mProgress));
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

    public void setRingWidth(float ringWidth) {
        this.mRingWidth = ringWidth;
        mRingView.setRingWidth(mRingWidth);
    }

    public void setRingOuterRadius(float ringOuterRadius) {
        this.mRingOuterRadius = ringOuterRadius;
        mRingView.setRingOuterRadius(mRingOuterRadius);
    }

    public void setStartColor(int startColor) {
        this.mStartColor = startColor;
        mRingView.setStartColor(mStartColor);
    }

    public void setEndColor(int endColor) {
        this.mEndColor = endColor;
        mRingView.setEndColor(mEndColor);
    }

    public void setOvalRingColor(int ovalRingColor) {
        this.mOvalRingColor = ovalRingColor;
        mOvalRingView.setOvalRingColor(mOvalRingColor);
    }

    public void endOvalRingAnim() {
        if (mOvalRingObjectAnimator != null) {
            mOvalRingObjectAnimator.end();
            mOvalRingObjectAnimator = null;
        }
    }
}
