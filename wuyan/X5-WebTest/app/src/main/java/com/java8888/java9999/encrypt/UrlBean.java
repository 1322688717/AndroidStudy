package com.java8888.java9999.encrypt;

import java.io.Serializable;

/**
 * @ClassName: UrlBean
 * @Author: CaoLong
 * @CreateDate: 2021/10/13 14:33
 * @Description:
 */
public class UrlBean implements Serializable {
    public String webViewUrl;
    //  用户服务协议地址
    public String privacyUrl;
    //  隐私政策地址
    public String policyUrl;
    //  悬浮按钮地址
    public String floatUrl;
    //  获取ip的地址
    public String ipUrl;
    //  ip后面的后缀
    public String urlSuffix;

    public String getWebViewUrl() {
        return webViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        this.webViewUrl = webViewUrl;
    }

    public String getPrivacyUrl() {
        return privacyUrl;
    }

    public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getFloatUrl() {
        return floatUrl;
    }

    public void setFloatUrl(String floatUrl) {
        this.floatUrl = floatUrl;
    }

    public String getIpUrl() {
        return ipUrl;
    }

    public void setIpUrl(String ipUrl) {
        this.ipUrl = ipUrl;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}
