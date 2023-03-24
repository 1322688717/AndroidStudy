package com.yxml8888.yxml9999.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;



//
///**
// * 有毒
// */
//public class URLRedirectHelper {
//
//
//    // 创建单例对象
//    private static volatile URLRedirectHelper sInstance;
//
//    /**
//     * 私有的构造方法
//     */
//    private URLRedirectHelper() {
//
//    }
//
//    /**
//     * 返回单例对象
//     */
//    public static URLRedirectHelper getInstance() {
//        if (sInstance == null) {
//            synchronized (URLRedirectHelper.class) {
//                if (sInstance == null) {
//                    sInstance = new URLRedirectHelper();
//                }
//            }
//        }
//        return sInstance;
//    }
//
//    /**
//     * 打开链接地址
//     *
//     * @param context  上下文对象
//     * @param urlLink  链接地址
//     * @param title    链接地址的标题
//     * @param isReport 是否上报进行统计，isReport值为true表示上报统计，反之亦然
//     * @return 返回true表示需要关闭当前的activity，返回false则表示不需要
//     */
//    public boolean openLink(Context context, String urlLink, String title, boolean isReport, boolean isOpenUrlInSystemBrowser, boolean hideShare) {
//        // 校验数据
//        if (context == null || TextUtils.isEmpty(urlLink)) {
//            return false;
//        }
//
//        startWebPage(context, urlLink, title, hideShare);
//        return false;
//    }
//
//
//    /**
//     * 启动跳转到网页页面
//     */
//    private void startWebPage(Context context, String url, String title, boolean hideShare) {
//        final Intent intent = new Intent(context, CustomWebActivity.class);
//        if (!TextUtils.isEmpty(url)) {
//            intent.putExtra("url", url);
//        }
//        if (!TextUtils.isEmpty(title)) {
//            intent.putExtra("title", title);
//        }
//        intent.putExtra("isHideShare", hideShare);
//        // 启动跳转到ACWeb
//        context.startActivity(intent);
//    }
//}
