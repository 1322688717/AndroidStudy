package com.example.kotlin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.LogUtils
import com.example.kotlin.api.RequestResponse
import com.example.kotlin.bean.SaoBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var erathy = MutableLiveData<String>()
    var sao = MutableLiveData<String>()

    fun setSao() {
        RequestResponse.vvhService.getSao("json").enqueue(object : Callback<SaoBean> {
            override fun onResponse(call: Call<SaoBean>, response: Response<SaoBean>) {
                sao.value = response.body()?.ishan
            }

            override fun onFailure(call: Call<SaoBean>, t: Throwable) {
                LogUtils.e(t.message)
            }
        })
    }
}
