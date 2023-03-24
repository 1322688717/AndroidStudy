package com.yxml8888.yxml9999.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tencent.mmkv.MMKV;
import com.yxml8888.yxml9999.R;

import com.yxml8888.yxml9999.encrypt.OneAesUtil;
import com.yxml8888.yxml9999.utils.SharePerfenceUtils;
import com.yxml8888.yxml9999.view.ByFullscreenHolder;
import com.yxml8888.yxml9999.view.DragFloatActionButton;
import com.yxml8888.yxml9999.view.NTSkipView;
import com.yxml8888.yxml9999.view.PrivacyProtocolDialog;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import me.jingbin.progress.WebProgress;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 *
 */
public class MainActivity extends Activity implements PrivacyProtocolDialog.ResponseCallBack {

    private long mLastClickBackTime;//上次点击back键的时间
    private DragFloatActionButton floatingButton;
    private RelativeLayout mLayoutMain;
    private FrameLayout mLayoutPrivacy;
    private WebView mWebView;
    private RelativeLayout mLayoutError;
    private Button mBtnReload;
    private String mWebViewUrl;
    private String mFloatUrl;
    private String mJson;
    private NTSkipView mTvSkip;
    private FrameLayout fullVideo;
    private View customView;
    private ByFullscreenHolder videoFullView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private WeakReference<Activity> mActivityWeakReference = null;
    // 修复可能部分h5无故横屏问题
    private boolean isFixScreenLandscape = false;
    // 修复可能部分h5无故竖屏问题
    private boolean isFixScreenPortrait = false;

    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;

    private WebProgress mProgress;
    private boolean bValue = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MMKV.initialize(this);
        //  修改状态栏颜色
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
        //  初始化view
        initViews();
        initProgess();
        initWebView();
        if (null != mWebView) {
            mWebView.clearHistory();
        }
        //  判断是否第一次启动
        boolean isFirst = SharePerfenceUtils.getInstance(this).getFirst();
        String switchHasBackground = getResources().getString(R.string.switchHasBackground);
        if (!TextUtils.isEmpty(switchHasBackground) && switchHasBackground.equals("true")) {
            mLayoutMain.setBackgroundResource(R.mipmap.screen);
            mLayoutPrivacy.setVisibility(View.GONE);
            mWebView.setVisibility(View.GONE);
            mProgress.setVisibility(View.GONE);
        } else {
            mLayoutMain.setBackgroundResource(R.color.white);
            mLayoutPrivacy.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
            mProgress.setVisibility(View.GONE);
        }
        String showPrivacy = getResources().getString(R.string.showPrivacy);
        if (isFirst &&  !TextUtils.isEmpty(showPrivacy) && showPrivacy.equals("true")) {
            mTvSkip.setVisibility(View.GONE);
            new PrivacyProtocolDialog(this, R.style.protocolDialogStyle, this).show();
        } else {
            showSkip();
        }
    }

    private void initProgess() {
        mProgress.show(); // 显示
        mProgress.setWebProgress(50);              // 设置进度
        mProgress.setColor("#D81B60");             // 设置颜色
        mProgress.setColor("#00D81B60","#D81B60"); // 设置渐变色
        mProgress.hide(); // 隐藏
    }

    private void showSkip(){
        initData();
        mTvSkip.setVisibility(View.VISIBLE);
        initWebView();
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvSkip.setText(String.format("跳过 %d", Math.round(millisUntilFinished / 1000f)));
            }

            @Override
            public void onFinish() {
                mTvSkip.setVisibility(View.GONE);
                mLayoutMain.setBackgroundResource(R.color.default_bg);
                mLayoutPrivacy.setVisibility(View.GONE);
                mWebView.setAnimation(new AlphaAnimation(0, 100));
                mWebView.setVisibility(View.VISIBLE);
                initFloating();
            }
        };
        countDownTimer.start();
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                mTvSkip.setVisibility(View.GONE);
                mLayoutMain.setBackgroundResource(R.color.default_bg);
                mLayoutPrivacy.setVisibility(View.GONE);
                mWebView.setAnimation(new AlphaAnimation(0, 10));
                mWebView.setVisibility(View.VISIBLE);
                initFloating();
            }
        });
    }
    private void initData() {
        String getIpConfig = getResources().getString(R.string.getIpConfig);
        String urlMain = getResources().getString(R.string.url_main);
        mJson = OneAesUtil.decrypt(urlMain);
        String suffix = getResources().getString(R.string.suffix);
        String urlSuffix = OneAesUtil.decrypt(suffix);
        String floatUrl = getResources().getString(R.string.floatUrl);
        mFloatUrl = OneAesUtil.decrypt(floatUrl);
        if (!TextUtils.isEmpty(getIpConfig) && getIpConfig.equals("true")) {
            final OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            final Request request = builder.url(mJson)
                    .get()
                    .build();

            final Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    skipError();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        String json = response.body().string();
                        JSONObject jsonObject = new JSONObject(json);
                        mWebViewUrl = jsonObject.getString("query");
                        if (!mWebViewUrl.contains("http")) {
                            mWebViewUrl = ("http://" + mWebViewUrl);
                        }
                        if (!TextUtils.isEmpty(urlSuffix)) {
                            mWebViewUrl = mWebViewUrl + urlSuffix;
                        }
                        if (TextUtils.isEmpty(mWebViewUrl)) {
                            skipError();
                            Toast.makeText(MainActivity.this, "配置异常，请检查", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        loadUrl(mWebViewUrl);
                    } catch (Exception e) {
                        skipError();
                    }
                }
            });
        } else {
            loadUrl(mJson);
        }

    }

    /**
     * 初始化View
     */
    private void initViews() {
        mLayoutMain = findViewById(R.id.layout_main);
        mLayoutPrivacy = findViewById(R.id.layout_privacy);
        mWebView = findViewById(R.id.web_view);
        floatingButton = findViewById(R.id.floatingButton);
        mLayoutError = findViewById(R.id.layout_error);
        mBtnReload = findViewById(R.id.btn_reload);
        mTvSkip = findViewById(R.id.tv_skip);
        fullVideo = findViewById(R.id.full_video);
        mProgress = findViewById(R.id.progressbar_view);
    }

    /**
     * 初始化悬浮按钮
     */
    private void initFloating() {
        final MMKV kv = MMKV.defaultMMKV();
        String showFloatButton = getResources().getString(R.string.showFloatButton);
        if (!TextUtils.isEmpty(showFloatButton) && showFloatButton.equals("true")) {
            floatingButton.setVisibility(View.VISIBLE);
            floatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(mFloatUrl)) {
                       // skipLocalBrowser(mFloatUrl);
                        if (bValue){
                            kv.encode("bool", false);
                            bValue = kv.decodeBool("bool");
                            //skipLocalBrowser(mFloatUrl);
                            loadUrl(mFloatUrl);
                            floatingButton.setBackgroundResource(R.mipmap.back);
                        }else {
                            kv.encode("bool", true);
                            bValue = kv.decodeBool("bool");
                            initData();
                            floatingButton.setBackgroundResource(R.mipmap.icon_float);
                        }
                    }
                }
            });
        }
    }

    /**
     * 跳转到浏览器
     */
    public void skipLocalBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mWebView && mWebView.canGoBack()) {
                mWebView.goBack();
//                final MMKV kv = MMKV.defaultMMKV();
//                if (bValue){
//                    kv.encode("bool", false);
//                    bValue = kv.decodeBool("bool");
//                    floatingButton.setBackgroundResource(R.mipmap.back);
//                }else {
//                    kv.encode("bool", true);
//                    bValue = kv.decodeBool("bool");
//                    floatingButton.setBackgroundResource(R.mipmap.icon_float);
//                }
                return false;
            } else {
                long curTime = System.currentTimeMillis();
                if (curTime - mLastClickBackTime > 2000) {
                    mLastClickBackTime = curTime;
                    Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                    return true;
                }
                finish();
                super.onBackPressed();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void agree() {
        //  直接跳转回去,之后不再弹出
        SharePerfenceUtils.getInstance(this).setFirst(false);
        showSkip();
    }

    @Override
    public void disAgree() {
        //  取消直接跳转回去,之后不再弹出
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLayoutMain.setBackgroundResource(R.mipmap.screen);
        mLayoutPrivacy.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
        initFloating();
    }

    /**
     * 初始化webView
     */
    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView() {
        try {
            WebSettings settings = mWebView.getSettings();
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
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            // 设置WebView加载页面文本内容的编码，默认“UTF-8”。
            settings.setDefaultTextEncodingName("UTF-8");
            mWebView.setWebViewClient(new MyWebViewClient());
            mWebView.setWebChromeClient(new MyWebChromeClient());
            mWebView.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUrl(String webUrl) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(webUrl);
                mProgress.show();
            }
        });
    }

    /**
     * 跳转错误页
     */
    public void skipError() {
        if (null == mLayoutError || null == mBtnReload) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayoutError.setVisibility(View.VISIBLE);
                mBtnReload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        });
    }

    public class MyWebViewClient extends WebViewClient {
        String currentUrl;

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            this.currentUrl = str;
            if (str.startsWith("http") || str.startsWith("https")) {
                return false;
            }
            try {
                Uri.parse(str);
                Intent parseUri = Intent.parseUri(str, Intent.URI_INTENT_SCHEME);
                parseUri.addCategory("android.intent.category.BROWSABLE");
                parseUri.setComponent(null);
                startActivity(parseUri);
            } catch (Exception unused) {
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override
        public void onPageFinished(final WebView webView, String str) {
            mProgress.hide();
            super.onPageFinished(webView, str);
        }

        @Override
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            AlertDialog create = new AlertDialog.Builder(MainActivity.this).create();
            create.setTitle("网络异常");
            create.setMessage("请切换到其他可用网络重新打开, 如果不行, 则清理应用数据后重试!");
            create.show();
        }
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onHideCustomView() {
            //退出全屏
            if (customView == null){
                return;
            }
            //移除全屏视图并隐藏
            fullVideo.removeView(customView);
            fullVideo.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//清除全屏

        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            //进入全屏
            customView = view;
            fullVideo.setVisibility(View.VISIBLE);
            fullVideo.addView(customView);
            fullVideo.bringToFront();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgress.setWebProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            uploadFiles = valueCallback;
            openFileChooseProcess();
            return true;
        }
    }

    public void openFileChooseProcess() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "选择文件"), 0);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 0) {
                if (uploadFile != null) {
                    uploadFile.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                    uploadFile = null;
                }
                if (uploadFiles != null) {
                    uploadFiles.onReceiveValue(new Uri[]{(intent == null || i2 != -1) ? null : intent.getData()});
                    uploadFiles = null;
                }
            }
        } else if (i2 == 0) {
            ValueCallback<Uri> valueCallback = this.uploadFile;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
                uploadFile = null;
            }
            ValueCallback<Uri[]> valueCallback2 = this.uploadFiles;
            if (valueCallback2 != null) {
                valueCallback2.onReceiveValue(null);
                uploadFiles = null;
            }
        }
    }


}
