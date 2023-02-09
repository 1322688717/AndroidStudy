package com.example.draggablepanenormal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.draggablepanenormal.fragment.BottomFragment
import com.example.draggablepanenormal.fragment.TopFragment
import com.hoanganhtuan95ptit.draggable.DraggablePanel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        draggablePanel.setDraggableListener(object : DraggablePanel.DraggableListener {
            override fun onChangeState(state: DraggablePanel.State) {
            }

            override fun onChangePercent(percent: Float) {
                alpha.alpha = 1 - percent
            }

        })

        supportFragmentManager.beginTransaction().add(R.id.frameFirst, TopFragment()).commit() // add frame top
        supportFragmentManager.beginTransaction().add(R.id.frameSecond, BottomFragment()).commit() // add frame bottom

        btnMax.setOnClickListener { draggablePanel.maximize() }// maximize
        btnMin.setOnClickListener { draggablePanel.minimize() }//minimizeo
        btnClose.setOnClickListener { draggablePanel.close() }//close


    }
}