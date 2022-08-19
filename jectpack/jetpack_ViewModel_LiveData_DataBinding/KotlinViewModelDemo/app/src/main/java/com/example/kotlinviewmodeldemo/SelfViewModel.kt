package com.example.kotlinviewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelfViewModel : ViewModel() {
    //声明一个私有变量number
    private val _number: MutableLiveData<Int> by lazy {
        //MutableLiveData是LiveData的一个容器，Int是数据类型
        MutableLiveData<Int>().also {
            it.value = 0
        }
    }
    val number: MutableLiveData<Int> get() = _number

    fun modifyNumber(n: Int) {
        _number.value = _number.value?.plus(n)
    }
}