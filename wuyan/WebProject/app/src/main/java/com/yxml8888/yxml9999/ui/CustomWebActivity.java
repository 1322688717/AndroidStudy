package com.yxml8888.yxml9999.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yxml8888.yxml9999.R;

public class CustomWebActivity extends AppCompatActivity {

    private TextView tvAdTitle;
    private ImageView ivAdBack;
    private ProgressBar progressBar;
    private WebView webView;
    private TextView tvAdReload;


    private String url;
    private String title = "内容详情";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_web);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
            View decorView = window.getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                decorView.setSystemUiVisibility(vis);
            }
        }
        initViews();
        initData();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        tvAdTitle = findViewById(R.id.tv_ad_title);
        ivAdBack = findViewById(R.id.iv_ad_back);
        progressBar = findViewById(R.id.pb_ad);
        webView = findViewById(R.id.wb_ad);
        tvAdReload = findViewById(R.id.tv_ad_reload);

        ivAdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAdReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
        initWebView(webView);
    }

    private void initWebView(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        // 设置WebView是否允许执行JavaScript脚本，默认false，不允许。
        settings.setJavaScriptEnabled(true);
        // 设置脚本是否允许自动打开弹窗，默认false，不允许。
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        settings.setAllowContentAccess(true);
        // 设置在WebView内部是否允许访问文件，默认允许访问。
        settings.setAllowFileAccess(true);
        // 设置WebView运行中的脚本可以是否访问任何原始起点内容，默认true
        settings.setAllowUniversalAccessFromFileURLs(true);
        // 设置WebView运行中的一个文件方案被允许访问其他文件方案中的内容，默认值true
        settings.setAllowFileAccessFromFileURLs(true);
        // 设置Application缓存API是否开启，默认false，设置有效的缓存路径参考setAppCachePath(String path)方法
        settings.setAppCacheEnabled(false);
        // 设置是否开启数据库存储API权限，默认false，未开启，可以参考setDatabasePath(String path)
        settings.setDatabaseEnabled(true);
        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM
        settings.setDomStorageEnabled(true);
        // 设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；
        // 当被设置为true，当前页面包含viewport属性标签，在标签中指定宽度值生效，如果页面不包含viewport标签，无法提供一个宽度值，这个时候该方法将被使用。
        settings.setUseWideViewPort(true);
        // 设置WebView是否支持多屏窗口，参考WebChromeClient#onCreateWindow，默认false，不支持。
        settings.setSupportMultipleWindows(false);
        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放。
        settings.setSupportZoom(false);
        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        settings.setBuiltInZoomControls(true);
        // 设置WebView加载页面文本内容的编码，默认“UTF-8”。
        settings.setDefaultTextEncodingName("UTF-8");
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    /**
     * 加载数据
     */
    private void initData() {
        // 获取传来的url
        try {
            url = getIntent().getExtras().getString("url");
        } catch (Exception e) {
            onBackPressed();
            return;
        }
        // 获取传来的title
        try {
            title = getIntent().getExtras().getString("title");
        } catch (Exception e) {
            title = "内容详情";
        }
        // 设置title
        tvAdTitle.setText(title);
        // 加载网页
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    public class MyWebViewClient extends WebViewClient {
        String currentUrl;
        boolean isInit = false;

        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            this.currentUrl = str;
            if (str.startsWith("http") || str.startsWith("https")) {
                return false;
            }
            try {
                Intent parseUri = Intent.parseUri(str, Intent.URI_INTENT_SCHEME);
                parseUri.addCategory("android.intent.category.BROWSABLE");
                parseUri.setComponent(null);
                CustomWebActivity.this.startActivity(parseUri);
            } catch (Exception unused) {
            }
            return true;
        }

        @Override
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            Log.e("WebView", "onReceivedSslError");
            sslErrorHandler.proceed();
        }

        @Override
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Log.d("WebView", "开始访问网页");
        }

        @Override
        public void onPageFinished(final android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        @Override
        public void onReceivedError(android.webkit.WebView webView, int i, String str, String str2) {
            AlertDialog create = new AlertDialog.Builder(CustomWebActivity.this).create();
            create.setTitle("网络异常");
            create.setMessage("请切换到其他可用网络重新打开, 如果不行, 则清理应用数据后重试!");
            create.show();
        }
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(android.webkit.WebView webView, int i) {


        }

        @Override
        public void onReceivedTitle(android.webkit.WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (str != null && !webView.getUrl().contains(str)) {
                tvAdTitle.setText(str);
            }
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }
    }


}