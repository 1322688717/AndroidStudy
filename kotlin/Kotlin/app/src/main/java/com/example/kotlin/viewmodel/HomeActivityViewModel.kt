package com.example.kotlin.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.ViewModel

class HomeActivityViewModel : ViewModel() {
    private var firstTime: Long = 0 // 记录点击返回时第一次的时间毫秒值
    fun exitApp(timeInterval: Long, mActivity: Activity) {
        // 第一次肯定会进入到if判断里面，然后把firstTime重新赋值当前的系统时间
        // 然后点击第二次的时候，当点击间隔时间小于2s，那么退出应用；反之不退出应用
        if (System.currentTimeMillis() - firstTime >= timeInterval) {
            Toast.makeText(mActivity, "再按一次推出程序", Toast.LENGTH_SHORT).show()
            firstTime = System.currentTimeMillis()
        } else {
            mActivity.finish() // 销毁当前activity
            System.exit(0) // 完全退出应用
        }
    }
}
