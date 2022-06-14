package com.example.jhweatherkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jhweatherkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var weatherViewModel : WeatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this


    }
}