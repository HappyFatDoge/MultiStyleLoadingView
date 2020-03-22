package com.fatdoge.loadingview.utils;

import android.content.Context;

/**
 * @author HappyFatWood 2575490085@qq.com
 * Created on 2020/3/12.
 */
public class DisplayUtils {

    /**
     * convert px to its equivalent dp
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2dp(Context context, float pxValue) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * convert dp to its equivalent px
     * @param context
     * @param dipValue
     * @return
     */
    public static float dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

    /**
     * convert px to its equivalent sp
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxValue / fontScale + 0.5f);
    }

    /**
     * convert sp to its equivalent px
     * @param context
     * @param spValue
     * @return
     */
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }
}
