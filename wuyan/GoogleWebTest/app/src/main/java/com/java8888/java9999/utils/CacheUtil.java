package com.java8888.java9999.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @ClassName: CacheUtil
 * @Author: CaoLong
 * @CreateDate: 2021/10/14 17:49
 * @Description:
 */
public class CacheUtil {

    /***
     * 清理所有缓存
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
