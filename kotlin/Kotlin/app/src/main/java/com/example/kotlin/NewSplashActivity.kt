package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.example.kotlin.databinding.ActivityMainBinding

class NewSplashActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        BarUtils.setStatusBarColor(this, getColor(R.color.red_802916))
        delayTime()
    }

    private fun delayTime() {
        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == 1) {
                    startActivity(Intent().setClass(this@NewSplashActivity, HomeActivity::class.java))
                    this@NewSplashActivity.finish()
                }
            }
        }
        val mMessage = Message()
        mMessage.what = 1
        handler.sendMessageDelayed(mMessage, 2000)
    }
}
