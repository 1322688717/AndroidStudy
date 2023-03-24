package com.java8888.java9999.encrypt;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.java8888.java9999.utils.OneAesUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @ClassName: AssetsUtils
 * @Author: CaoLong
 * @CreateDate: 2021/10/13 14:27
 * @Description:
 */
public class AssetsUtils {
    /**
     * 读取Assest文件夹下资源，返回Properties。若为加密数据则自动解密
     */
    public static String getAssetsString(Context context, String filepath) {
        try {
            // 读取assets数据
            InputStream inputStream = context.getAssets().open(filepath);
            return inputStreamToString(inputStream);
        } catch (Exception e) {
            Log.e("AssetProperty", e.toString());
        }
        return null;
    }

    /**
     * InputStream -> Byte
     */
    private static final byte[] inputStreamToByte(InputStream in) {
        byte[] bytes = {};

        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int count = 0;
            while ((count = in.read(data, 0, 1024)) > 0) {
                byteOutStream.write(data, 0, count);
            }

            bytes = byteOutStream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * InputStream -> String
     */
    private static String inputStreamToString(InputStream in) {
        String data = "";

        byte[] bytes = inputStreamToByte(in);
        data = new String(bytes);

        return data;
    }

    /**
     * 从assets加密文件中获取UrlBean
     *
     * @param context
     * @return UrlBean
     */
    public static UrlBean getUrlBeanFromAssets(Context context) {
        String data = AssetsUtils.getAssetsString(context, "y.x");
        try {
            String s = OneAesUtil.decrypt(data);
            return JSON.parseObject(s, UrlBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
