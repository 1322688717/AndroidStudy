package com.java8888.java9999.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;

/**
 * SharePerfence工具类
 */
public class SharePerfenceUtils {
    private static SharePerfenceUtils instance = null;
    private SharedPreferences sp;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SharePerfenceUtils(Context mContext) {
        sp = mContext.getSharedPreferences("fourp", Context.MODE_PRIVATE);
    }

    public static SharePerfenceUtils getInstance(Context mContext) {
        synchronized (SharePerfenceUtils.class) {
            if (null == instance) {
                instance = new SharePerfenceUtils(mContext);
            }
            return instance;
        }
    }

    public void setKV(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getKV(String key) {
        return sp.getString(key, "");
    }


    public void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void setLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public Long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
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
