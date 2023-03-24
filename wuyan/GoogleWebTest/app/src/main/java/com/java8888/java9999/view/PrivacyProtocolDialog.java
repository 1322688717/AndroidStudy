package com.java8888.java9999.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.Toast;

import com.java8888.java9999.R;
import com.java8888.java9999.encrypt.AssetsUtils;
import com.java8888.java9999.encrypt.UrlBean;
import com.java8888.java9999.utils.DesentyUtil;
import com.java8888.java9999.utils.URLRedirectHelper;

import java.lang.ref.WeakReference;

public class PrivacyProtocolDialog extends Dialog implements View.OnClickListener {

    LollipopFixedWebView mWebView;
    private final Context mContext;
    private ResponseCallBack mResponseCallBack;

    private final WeakReference<Context> mContextWeakReference;

    public PrivacyProtocolDialog(Context context, int theme, ResponseCallBack responseCallBack) {
        super(context, theme);
        mContextWeakReference = new WeakReference<>(context);
        mContext = mContextWeakReference.get();
        mResponseCallBack = responseCallBack;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_protocol);
        mWebView = findViewById(R.id.web_view);
        findViewById(R.id.disagree_tv).setOnClickListener(this);
        findViewById(R.id.agree_tv).setOnClickListener(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = DesentyUtil.Dp2Px(mContext, 270);
        lp.height = DesentyUtil.Dp2Px(mContext, 350);
        getWindow().setAttributes(lp);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //与js交互必须设置
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/privacy_protocol.html");
        mWebView.addJavascriptInterface(this, "protocol");
        mWebView.setVerticalScrollBarEnabled(false);
    }

    @JavascriptInterface
    public void jump() {
        UrlBean urlBean = AssetsUtils.getUrlBeanFromAssets(mContext);
        if (urlBean != null) {
            URLRedirectHelper.getInstance().openLink(mContext, urlBean.getPrivacyUrl(), "", false, false, true);
        } else {
            Toast.makeText(mContext, "配置异常，请检查", Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public void jumpToUserAgreement() {
//        URLRedirectHelper.getInstance().openLink(mContext, AppConfig.privacyUrl, "", false, false, true);
    }

    @JavascriptInterface
    public void jumpToOtherAgreement() {
        UrlBean urlBean = AssetsUtils.getUrlBeanFromAssets(mContext);
        if (urlBean != null) {
            URLRedirectHelper.getInstance().openLink(mContext, urlBean.getPolicyUrl(), "", false, false, true);
        } else {
            Toast.makeText(mContext, "配置异常，请检查", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.destroy();
            mWebView = null;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.disagree_tv:
                mResponseCallBack.disAgree();
                dismiss();
                break;
            case R.id.agree_tv:
                mResponseCallBack.agree();
                dismiss();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public interface ResponseCallBack {
        void agree();

        void disAgree();
    }
}
