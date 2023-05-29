package com.example.a32_textview

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RunAway2Activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_away2)
        val marqueeTextView = findViewById<TextView>(R.id.marqueeTextView)
        val translateY = -marqueeTextView.height.toFloat() // 设置竖直方向的位移距离
        val animator = ObjectAnimator.ofFloat(marqueeTextView, "translationY", 2200f, -2200f)
        animator.duration = 8000 // 设置动画的持续时间，单位为毫秒
        animator.repeatCount = ValueAnimator.INFINITE // 设置动画重复次数为无限循环
        animator.repeatMode = ValueAnimator.RESTART // 设置动画重复模式为重新开始
        animator.start()
    }
}