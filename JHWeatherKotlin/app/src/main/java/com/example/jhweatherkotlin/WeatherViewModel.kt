package com.example.jhweatherkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {
    private var city : MutableLiveData<String> =  MutableLiveData()



}