package com.java8888.java9999.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * WebView的工具类
 *
 * @author Firry
 */
public class WebViewUtil {
    public static void configWebView(Context mContext, WebView webView) {
        if (webView != null) {
            /* 添加webView配置 */
            final WebSettings settings = webView.getSettings();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                settings.setAllowContentAccess(true);
            }
            settings.setAllowFileAccess(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                settings.setAllowUniversalAccessFromFileURLs(true);
                settings.setAllowFileAccessFromFileURLs(true);
            }
            settings.setAppCacheEnabled(true);
            settings.setAppCachePath(mContext.getDatabasePath("cache-webview").getAbsolutePath());
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheEnabled(false);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setSupportMultipleWindows(false);
            settings.setLoadWithOverviewMode(true);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(false);
            //设置编码
            webView.getSettings().setDefaultTextEncodingName("utf-8");
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString());
        }
    }
}
