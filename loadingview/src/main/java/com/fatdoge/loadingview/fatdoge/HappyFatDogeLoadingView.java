package com.fatdoge.loadingview.fatdoge;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fatdoge.loadingview.R;
import com.fatdoge.loadingview.utils.ConstantUtils;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/13.
 */
public class HappyFatDogeLoadingView extends RelativeLayout {

    private Context mContext;
    private ImageView mImageExpression;
    private ImageView mImageBubble;
    private TextView mTxtProgress;
    private TextView mTxtDescription;

    private boolean mWithProgressDisplay;
    private int mProgress;
    private float mProgressTextSize;
    private int mProgressTextColor;
    private String mDescription;
    private float mDesTextSize;
    private int mDesTextColor;
    private int mCurrentType = ConstantUtils.TYPE_ERROR;

    private final boolean DEFAULT_WITH_PROGRESS_DISPLAY = false;

    public HappyFatDogeLoadingView(Context context) throws Exception {
        this(context, null);
    }

    public HappyFatDogeLoadingView(Context context, AttributeSet attrs) throws Exception {
        this(context, attrs, 0);
    }

    public HappyFatDogeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        View rootView = inflate(mContext, R.layout.view_happy_fat_doge_loading_layout, this);
        initView(rootView);
    }

    private void initAttrs(AttributeSet attributeSet) {
        Resources resources = mContext.getResources();
        TypedArray typedArray = mContext.obtainStyledAttributes(
                attributeSet, R.styleable.HappyFatDogeLoadingView);
        mWithProgressDisplay = typedArray.getBoolean(
                R.styleable.HappyFatDogeLoadingView_withProgressDisplay, DEFAULT_WITH_PROGRESS_DISPLAY);
        mProgress = typedArray.getInteger(R.styleable.HappyFatDogeLoadingView_progress,
                ConstantUtils.DEFAULT_PROGRESS);
        mProgressTextSize = typedArray.getDimension(R.styleable.HappyFatDogeLoadingView_progressTextSize,
                ConstantUtils.DEFAULT_PROGRESS_TEXT_SIZE);
        mProgressTextColor = typedArray.getColor(R.styleable.HappyFatDogeLoadingView_progressTextColor,
                resources.getColor(R.color.color_E6CA7E));
        mDescription = typedArray.getString(R.styleable.HappyFatDogeLoadingView_progressDescription);
        mDesTextSize = typedArray.getDimension(R.styleable.HappyFatDogeLoadingView_desTextSize,
                ConstantUtils.DEFAULT_DES_TEXT_SIZE);
        mDesTextColor = typedArray.getColor(R.styleable.HappyFatDogeLoadingView_desTextColor,
                resources.getColor(R.color.color_FFFFFF));
        typedArray.recycle();
    }

    private void initView(View rootView) throws Exception {
        mImageExpression = rootView.findViewById(R.id.image_expression);
        mImageBubble = rootView.findViewById(R.id.image_bubble);
        mTxtProgress = rootView.findViewById(R.id.txt_progress);
        mTxtDescription = rootView.findViewById(R.id.txt_description);

        if (!mWithProgressDisplay) {
            setThinkingAnim();
        } else {
            mImageBubble.setVisibility(VISIBLE);
            mTxtProgress.setVisibility(VISIBLE);
            initProgress();
        }
        initDescription();
    }

    private void setThinkingAnim() {
        if (mWithProgressDisplay) {
            return;
        }
        mImageExpression.setImageResource(R.drawable.anim_fat_doge_thinking);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageExpression.getDrawable();
        animationDrawable.start();
    }

    private void initProgress() throws Exception {
        if (!mWithProgressDisplay) {
            return;
        }
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
        if (!mWithProgressDisplay) {
            return;
        }
        if (mProgress < ConstantUtils.MIN_PROGRESS
                || mProgress > ConstantUtils.MAX_PROGRESS) {
            throw new Exception("Progress must be between 0-100");
        }
        if (mProgress < ConstantUtils.HALF_THRESHOLD) {
            if (mCurrentType != ConstantUtils.TYPE_SAD) {
                setExpression(R.drawable.fat_doge_cry, R.drawable.fat_doge_cry_tears);
                mCurrentType = ConstantUtils.TYPE_SAD;
            }
        } else if (mProgress < ConstantUtils.FOUR_FIFTHS_THRESHOLD) {
            if (mCurrentType != ConstantUtils.TYPE_ANGRY) {
                setExpression(R.drawable.fat_doge_angry, R.drawable.fat_doge_angry_bubble);
                mCurrentType = ConstantUtils.TYPE_ANGRY;
            }
        } else if (mCurrentType != ConstantUtils.TYPE_HAPPY){
            setExpression(R.drawable.fat_doge_leisurely, R.drawable.fat_doge_leisurely_bubble);
            mCurrentType = ConstantUtils.TYPE_HAPPY;
        }
    }

    private void setExpression(int resource, int animResource) {
        if (!mWithProgressDisplay) {
            return;
        }
        mImageExpression.setImageResource(resource);
        setBubbleAnim(animResource);
    }

    private void setBubbleAnim(int resourceID) {
        if (!mWithProgressDisplay) {
            return;
        }
        mImageBubble.setImageResource(resourceID);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mImageBubble, "alpha", 1f, 0.2f);
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    public void setProgress(int progress) throws Exception {
        if (!mWithProgressDisplay) {
            return;
        }
        if (progress < ConstantUtils.MIN_PROGRESS || progress > ConstantUtils.MAX_PROGRESS) {
            throw new Exception("Progress must be between 0-100");
        }
        mProgress = progress;
        String progressTxt = mProgress + "%";
        mTxtProgress.setText(progressTxt);
        setExpression();
    }

    public void setProgressTextSize(float textSize) {
        if (!mWithProgressDisplay) {
            return;
        }
        setProgressTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setProgressTextSize(int unit, float textSize) {
        if (!mWithProgressDisplay) {
            return;
        }
        mProgressTextSize = textSize;
        mTxtProgress.setTextSize(unit, mProgressTextSize);
    }

    public void setProgressTextColor(int color) {
        if (!mWithProgressDisplay) {
            return;
        }
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

    public void finish() {
        if (!mWithProgressDisplay) {
            mImageExpression.setImageResource(R.drawable.fat_doge_thinking_finish);
        }
    }
}
