/*
 * Created by ShawnAnn on 16-11-25 上午10:56
 * This is a personal tool library for usual coding,if you have any good idea,welcome pull requests.
 * My email : annshawn518@gamil.com
 * My QQ：1904508978
 * Copyright (c) 2016. All rights reserved.
 */

package com.yxml8888.yxml9999.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 转换相关工具类
 *
 * @author ShawnAnn
 * @since 2016/3/3.
 */
public class DesentyUtil {

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}