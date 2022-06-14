package com.example.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OKHttp {

    /**
     * 同步get请求
     * @param url
     * @return
     */
   public static String get(String url){
       OkHttpClient okHttpClient = new OkHttpClient();
       Request request = new Request.Builder()
               .url(url)
               .build();
               try{
                   return okHttpClient.newCall(request).execute().body().string();
               }catch (Exception e){
                   e.printStackTrace();
               }
               return "fd";
   }
}
