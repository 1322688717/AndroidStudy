package com.example.a32_textview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RunAwayActivity : AppCompatActivity() {
    var runAway: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_away)
        runAway = findViewById<TextView>(R.id.tv_runAway)
        runAway?.isSelected = true
    }
}
