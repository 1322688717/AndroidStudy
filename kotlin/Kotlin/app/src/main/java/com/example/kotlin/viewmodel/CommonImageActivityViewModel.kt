package com.example.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.LogUtils
import com.example.kotlin.api.RequestResponse
import com.example.kotlin.bean.Bean60s
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonImageActivityViewModel : ViewModel() {
    var bean60s: MutableLiveData<Bean60s>? = null
    var imgUrl: MutableLiveData<String>? = null

    fun getBean60s() {
        RequestResponse.vvhService.get60s("json").enqueue(object : Callback<Bean60s> {
            override fun onResponse(call: Call<Bean60s>, response: Response<Bean60s>) {
                imgUrl?.value = response.body()?.imgUrl
            }

            override fun onFailure(call: Call<Bean60s>, t: Throwable) {
                LogUtils.e("获取“每天60秒读懂世界”失败")
            }
        })
    }
}
