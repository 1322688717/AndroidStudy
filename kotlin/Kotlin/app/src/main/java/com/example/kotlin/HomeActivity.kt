package com.example.kotlin

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.blankj.utilcode.util.BarUtils
import com.example.kotlin.databinding.ActivityHomeBinding
import com.example.kotlin.ui.home.HomeViewModel
import com.example.kotlin.viewmodel.HomeActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val manager: FragmentManager = supportFragmentManager
    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeActivityViewModel::class.java]
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
        BarUtils.setStatusBarColor(this, getColor(R.color.white))
        BarUtils.setStatusBarLightMode(this, true)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 点击了返回按键
            if (manager.backStackEntryCount != 0) {
                manager.popBackStack()
            } else {
                viewModel.exitApp(2000, this)
            }
            return true // 返回true，防止该事件继续向下传播
        }
        return super.onKeyDown(keyCode, event)
    }
}
