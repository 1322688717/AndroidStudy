package com.example.kotlin.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity(), LifecycleOwner {

    var binding: ActivityWeatherBinding? = null
    var viewModel: WeatherViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        initData()
    }

    private fun initData() {
        viewModel?.getWeather()
        viewModel?.city?.observe(
            this,
            Observer {
                binding?.tvCity?.text = it
            },
        )

        viewModel?.high?.observe(
            this,
            Observer {
                binding?.tvHigh?.text = it
            },
        )

        viewModel?.low?.observe(
            this,
            Observer {
                binding?.tvLow?.text = it
            },
        )

        viewModel?.type?.observe(
            this,
            Observer {
                binding?.tvType?.text = it
            },
        )
    }
}
