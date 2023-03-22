package com.example.kotlin.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlin.databinding.ActivityCommonImageBinding
import com.example.kotlin.viewmodel.CommonImageActivityViewModel

class CommonImageActivity : AppCompatActivity(), LifecycleOwner {

    var binding: ActivityCommonImageBinding? = null
    var viewModel: CommonImageActivityViewModel? = null
    var imgUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonImageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = ViewModelProvider(this)[CommonImageActivityViewModel::class.java]
        initData()
    }

    private fun initData() {
        viewModel?.getBean60s()
        viewModel?.imgUrl?.observe(
            this,
            Observer {
                Glide.with(this)
                    .load(it)
                    .into(binding!!.imageView)
            },
        )
    }
}
