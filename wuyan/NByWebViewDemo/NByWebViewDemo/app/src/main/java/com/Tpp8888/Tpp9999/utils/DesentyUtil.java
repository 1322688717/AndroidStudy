package com.Tpp8888.Tpp9999.utils;

import android.content.Context;

public class DesentyUtil {

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}