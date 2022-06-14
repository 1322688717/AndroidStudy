package com.example.mvvmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var a : Int = 10
    var b : Int = 50
    val c : String = "我是c"
    val d : String = "我是d"
    var sum : Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //ViewModel绑定
        val selfViewModel: SelfViewModel = ViewModelProvider(this).get(SelfViewModel::class.java)

        //dataBinding 将View上的控件绑定到binding
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.data = selfViewModel //给xml文件中的data赋值
        binding.lifecycleOwner = this //生命周期

        all()
    }

    private fun all() {

        sum = a+b
        Log.w("TAG","abcd====="+a+1)
        Log.w("TAG","abcd====="+b+1)
        Log.w("TAG","sum====="+sum)
        Log.w("TAG","sum====="+sum)

        fun number (rkd : Int) : String{

            return "sdfs"
        }

    }
}