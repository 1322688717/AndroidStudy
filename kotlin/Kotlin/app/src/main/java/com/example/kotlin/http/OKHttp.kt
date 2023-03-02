package com.example.kotlin.http

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class OKHttp {
    lateinit var TpyeUrl: String
    private var instance: OKHttp? = null
    fun getInstance(): OKHttp? {
        if (instance == null) {
            instance = OKHttp()
        }
        return instance
    }

    var request: Request? = null
    var okHttpClient: OkHttpClient? = null
    val TAG = "zcq"

    fun getUrl(tpye: Int): String {
        when (tpye) {
            1 -> "https://service.picasso.adesk.com/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot"
            else -> "123"
        }
        return TpyeUrl
    }

    fun getOKHttp(type: Int): OkHttpClient {
        val request = Request.Builder().url(getUrl(type)).build()
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).build()

        return client
    }

    fun getHead(listener: IGetDataListener<String>, activity: Activity) {
        val sharedPreferences: SharedPreferences =
            activity.getSharedPreferences("sp", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "1")
        Log.e("TAG", "拿token===$token")
        request = Request.Builder()
            // .url("http://121.5.233.252/prod-api/system/user/profile")
            .url("http://47.98.113.125:8082/system/user/profile")
            .addHeader(
                "Authorization",
                "Bearer $token",
            )
            .get()
            .build()
        val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor {
            Log.e("拦截器", it)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        okHttpClient!!.newCall(request!!).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                listener.onFailure(e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                listener.onSuccess(response.toString())
            }
        })
    }
}
