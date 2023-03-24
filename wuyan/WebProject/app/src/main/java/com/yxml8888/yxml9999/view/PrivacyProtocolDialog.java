package com.yxml8888.yxml9999.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.yxml8888.yxml9999.R;
import com.yxml8888.yxml9999.encrypt.OneAesUtil;
import com.yxml8888.yxml9999.ui.CustomWebActivity;
import com.yxml8888.yxml9999.utils.DesentyUtil;


import java.lang.ref.WeakReference;

public class PrivacyProtocolDialog extends Dialog implements View.OnClickListener {

    private WebView mWebView;
    private final Context mContext;
    private ResponseCallBack mResponseCallBack;
    private String mPrivacyUrl;
    private String mPolicyUrl;
    private final WeakReference<Context> mContextWeakReference;

    public PrivacyProtocolDialog(Context context, int theme, ResponseCallBack responseCallBack) {
        super(context, theme);
        mContextWeakReference = new WeakReference<>(context);
        mContext = mContextWeakReference.get();
        mResponseCallBack = responseCallBack;
        String privacyUrl = context.getResources().getString(R.string.privacyUrl);
        this.mPrivacyUrl= OneAesUtil.decrypt(privacyUrl);
        String policyUrl = context.getResources().getString(R.string.policyUrl);
        this.mPolicyUrl= OneAesUtil.decrypt(policyUrl);
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
        if (!TextUtils.isEmpty(mPrivacyUrl)) {
            //URLRedirectHelper.getInstance().openLink(mContext, mPrivacyUrl, "用户服务协议", false, false, true);
            Intent intent = new Intent(mContext, CustomWebActivity.class);

                intent.putExtra("url", mPrivacyUrl);

                intent.putExtra("title", "用户服务协议");

            intent.putExtra("isHideShare", true);
            // 启动跳转到ACWeb
            mContext.startActivity(intent);
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
        if (!TextUtils.isEmpty(mPolicyUrl)) {
            //URLRedirectHelper.getInstance().openLink(mContext, mPolicyUrl, "隐私政策", false, false, true);
            Intent intent = new Intent(mContext, CustomWebActivity.class);

            intent.putExtra("url", mPrivacyUrl);

            intent.putExtra("title", "隐私政策");

            intent.putExtra("isHideShare", true);
            // 启动跳转到ACWeb
            mContext.startActivity(intent);
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
