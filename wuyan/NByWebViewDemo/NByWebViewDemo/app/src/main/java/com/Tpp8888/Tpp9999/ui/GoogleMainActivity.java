package com.Tpp8888.Tpp9999.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.Tpp8888.Tpp9999.R;
import com.Tpp8888.Tpp9999.config.MyJavascriptInterface;
import com.Tpp8888.Tpp9999.config.MyWebChromeClient;
import com.Tpp8888.Tpp9999.utils.OneAesUtil;
import com.Tpp8888.Tpp9999.utils.SPUtil;
import com.Tpp8888.Tpp9999.view.DragFloatActionButton;
import com.Tpp8888.Tpp9999.view.NTSkipView;
import com.Tpp8888.Tpp9999.view.PrivacyProtocolDialog;
import com.tencent.mmkv.MMKV;

import org.json.JSONObject;

import java.io.IOException;

import me.jingbin.web.ByWebTools;
import me.jingbin.web.ByWebView;
import me.jingbin.web.OnByWebClientCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 *
 */
public class GoogleMainActivity extends Activity implements PrivacyProtocolDialog.ResponseCallBack {

    private long mLastClickBackTime;//上次点击back键的时间
    private DragFloatActionButton floatingButton;
    private RelativeLayout mLayoutMain;
    private FrameLayout mLayoutPrivacy;
    private FrameLayout mWebViewLayout;
    private WebView mWebView;
    private ByWebView byWebView;
    private RelativeLayout mLayoutError;
    private Button mBtnReload;
    private String mWebViewUrl;
    private String mFloatUrl;
    private String mJson;
    private NTSkipView mTvSkip;

    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;
    private CountDownTimer countDownTimer;
    private String urlSuffix;
    private boolean bValue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        //  初始化view
        initViews();
        initWeb();
        if (null != mWebView) {
            mWebView.clearHistory();
        }
        //  判断是否第一次启动
        int version = (int) SPUtil.get(this,"version",0);

        String switchHasBackground = getResources().getString(R.string.switchHasBackground);
        if (!TextUtils.isEmpty(switchHasBackground) && switchHasBackground.equals("true")) {
            mLayoutMain.setBackgroundResource(R.mipmap.screen);
            mLayoutPrivacy.setVisibility(View.GONE);
            mWebViewLayout.setVisibility(View.GONE);
        } else {
            mLayoutMain.setBackgroundResource(R.color.white);
            mLayoutPrivacy.setVisibility(View.VISIBLE);
            mWebViewLayout.setVisibility(View.GONE);
        }
        String showPrivacy = getResources().getString(R.string.showPrivacy);
        if (version<=0 && !TextUtils.isEmpty(showPrivacy) && showPrivacy.equals("true")) {
            mTvSkip.setVisibility(View.GONE);
            new PrivacyProtocolDialog(this, R.style.protocolDialogStyle, this).show();
        } else {
            showSkip();
        }
    }

    private void showSkip() {
        initData();
        mTvSkip.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvSkip.setText(String.format("跳过 %d", Math.round(millisUntilFinished / 1000f)));
            }

            @Override
            public void onFinish() {
                mTvSkip.setVisibility(View.GONE);
                mLayoutMain.setBackgroundResource(R.color.default_bg);
                mLayoutPrivacy.setVisibility(View.GONE);
                mWebViewLayout.setAnimation(new AlphaAnimation(0, 100));
                mWebViewLayout.setVisibility(View.VISIBLE);
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
                mWebViewLayout.setAnimation(new AlphaAnimation(0, 10));
                mWebViewLayout.setVisibility(View.VISIBLE);
                initFloating();
            }
        });
    }

    private void initData() {
        String getIpConfig = getResources().getString(R.string.getIpConfig);
        String urlMain = getResources().getString(R.string.url_main);
        mJson = OneAesUtil.decrypt(urlMain);
        String suffix = getResources().getString(R.string.suffix);
        urlSuffix = OneAesUtil.decrypt(suffix);
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
                    Log.e("TAG",e.toString());
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
                            Toast.makeText(GoogleMainActivity.this, "配置异常，请检查", Toast.LENGTH_SHORT).show();
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
        mWebViewLayout = findViewById(R.id.web_view);
        floatingButton = findViewById(R.id.floatingButton);
        mLayoutError = findViewById(R.id.layout_error);
        mBtnReload = findViewById(R.id.btn_reload);
        mTvSkip = findViewById(R.id.tv_skip);
    }

    /**
     * 初始化悬浮按钮
     */
    private void initFloating() {
        String showFloatButton = getResources().getString(R.string.showFloatButton);
        final MMKV kv = MMKV.defaultMMKV();
        bValue = true;
        if (!TextUtils.isEmpty(showFloatButton) && showFloatButton.equals("true")) {
            floatingButton.setVisibility(View.VISIBLE);
            floatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (!TextUtils.isEmpty(mFloatUrl)) {
//
//                        skipLocalBrowser(mFloatUrl);
//                    }

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
        startActivity(intent);
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mWebView && mWebView.canGoBack()) {
                mWebView.goBack();
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
        SPUtil.put(this,"version",1);
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
        mWebViewLayout.setVisibility(View.VISIBLE);
        initFloating();
   }

   private void initWeb(){
       byWebView = ByWebView
               .with(GoogleMainActivity.this)
               .setWebParent(mWebViewLayout, new LinearLayout.LayoutParams(-1, -1))
               .useWebProgress(ContextCompat.getColor(GoogleMainActivity.this, R.color.colorPrimary))
               .addJavascriptInterface("injectedObject", new MyJavascriptInterface(GoogleMainActivity.this))
               .setOnByWebClientCallback(onByWebClientCallback)
               .get();
       mWebView = byWebView.getWebView();
   }

    private void loadUrl(final String webUrl) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(webUrl);
                Log.e("TAG","地址 ： "+webUrl);
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
                        startActivity(new Intent(GoogleMainActivity.this, GoogleMainActivity.class));
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode,resultCode,intent);
        byWebView.handleFileChooser(requestCode, resultCode, intent);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, GoogleMainActivity.class));
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (byWebView!=null){
            byWebView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (byWebView!=null){
            byWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        if (byWebView!=null){
            byWebView.onDestroy();
        }
        super.onDestroy();
    }


    private OnByWebClientCallback onByWebClientCallback = new OnByWebClientCallback() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.e("---onPageStarted", url);
        }

        @Override
        public boolean onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // 如果自己处理，需要返回true
            return super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e("---onPageFinished", url);
            // 网页加载完成后的回调
//            loadImageClickJs();
//            loadTextClickJs();
//            loadWebsiteSourceCodeJs();

        }

        @Override
        public boolean isOpenThirdApp(String url) {
            // 处理三方链接
            Log.e("---url", url);
//            try {
//                Uri.parse(url);
//                Intent parseUri = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
//                parseUri.addCategory("android.intent.category.BROWSABLE");
//                parseUri.setComponent(null);
//                startActivity(parseUri);
//            } catch (Exception unused) {
//            }
            return ByWebTools.handleThirdApp(GoogleMainActivity.this, url);
        }
    };

    /**
     * 前端注入JS：
     * 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
     */
    private void loadImageClickJs() {
        byWebView.getLoadJsHolder().loadJs("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"));}" +
                "}" +
                "})()");
    }

    /**
     * 前端注入JS：
     * 遍历所有的<li>节点,将节点里的属性传递过去(属性自定义,用于页面跳转)
     */
    private void loadTextClickJs() {
        byWebView.getLoadJsHolder().loadJs("javascript:(function(){" +
                "var objs =document.getElementsByTagName(\"li\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){" +
                "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" +
                "}" +
                "})()");
    }

    /**
     * get website source code
     * 获取网页源码
     */
    private void loadWebsiteSourceCodeJs() {
        byWebView.getLoadJsHolder().loadJs("javascript:window.injectedObject.showSource(document.getElementsByTagName('html')[0].innerHTML);");
    }

    /**
     * 作为三方浏览器打开传过来的值
     * Scheme: https
     * host: www.jianshu.com
     * path: /p/1cbaf784c29c
     * url = scheme + "://" + host + path;
     */
    private void getDataFromBrowser(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            try {
                String scheme = data.getScheme();
                String host = data.getHost();
                String path = data.getPath();
                String text = "Scheme: " + scheme + "\n" + "host: " + host + "\n" + "path: " + path;
                Log.e("data", text);
                String url = scheme + "://" + host + path;
                byWebView.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getDataFromBrowser(intent);
    }
}
