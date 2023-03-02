package com.example.kotlin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin.adapter.AdapterLike
import com.example.kotlin.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private val url: String =
        "https://service.picasso.adesk.com/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot"
    private lateinit var viewModel: WallPaperViewModel
    private var binding: FragmentDashboardBinding? = null
    private var adapter: AdapterLike? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(WallPaperViewModel::class.java)
        initView()
        return binding!!.root
    }

    private fun initView() {
        viewModel.setLikeBean(url, requireActivity())
        binding!!.rcWallpaper.layoutManager = GridLayoutManager(activity, 2)
        viewModel.likeBean.observe(
            viewLifecycleOwner,
            Observer {
                adapter = AdapterLike(it.res.vertical, requireActivity())
                binding!!.rcWallpaper.adapter = adapter
            },
        )
    }
}
