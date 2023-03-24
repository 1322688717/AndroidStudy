package com.yxml8888.yxml9999.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * SharePerfence工具类
 */
public class SharePerfenceUtils {
    private static SharePerfenceUtils instance = null;
    private SharedPreferences sp;

    private SharePerfenceUtils(Context mContext) {
        sp = mContext.getSharedPreferences("firstsp", Context.MODE_PRIVATE);
    }

    public static SharePerfenceUtils getInstance(Context mContext) {
        synchronized (SharePerfenceUtils.class) {
            if (null == instance) {
                instance = new SharePerfenceUtils(mContext);
            }
            return instance;
        }
    }


    public void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    /**
     * 将idfa保存在本地
     *
     * @param isFirst
     */
    public void setFirst(boolean isFirst) {
        setBoolean("isFirst", isFirst);
    }

    /**
     * 获取本地保存的uuid
     *
     * @return
     */
    public boolean getFirst() {
        return getBoolean("isFirst",true);
    }

}
