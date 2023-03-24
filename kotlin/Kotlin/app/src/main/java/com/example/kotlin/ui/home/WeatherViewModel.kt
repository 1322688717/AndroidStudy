package com.example.kotlin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.LogUtils
import com.example.kotlin.api.RequestResponse
import com.example.kotlin.bean.WeatherBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    var city = MutableLiveData<String>()

    var high = MutableLiveData<String>()

    var low = MutableLiveData<String>()

    var type = MutableLiveData<String>()

    fun getWeather() {
        RequestResponse.vvhService.getWeather("json").enqueue(object : Callback<WeatherBean> {
            override fun onResponse(call: Call<WeatherBean>, response: Response<WeatherBean>) {
                city?.value = response.body()?.city
                high?.value = response.body()?.info?.high
                low?.value = response.body()?.info?.low
                type?.value = response.body()?.info?.type
                LogUtils.e("response==$response")
                LogUtils.e("response.body()==${response.body()}")
                LogUtils.e("response.body()?.city==${response.body()?.city}")
                LogUtils.e("response.body()?.city==${response.body()?.info?.high}")
                LogUtils.e("city==$city")
            }

            override fun onFailure(call: Call<WeatherBean>, t: Throwable) {
                LogUtils.e("$t")
            }
        })
    }
}
