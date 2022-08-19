package com.example.kotlinviewmodeldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinviewmodeldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        //ViewModel绑定
        val selfViewModel: SelfViewModel = ViewModelProvider(this).get(SelfViewModel::class.java)

        //dataBinding 将View上的控件绑定到binding
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.data = selfViewModel //给xml文件中的data赋值
        binding.lifecycleOwner = this //生命周期
    }
}
