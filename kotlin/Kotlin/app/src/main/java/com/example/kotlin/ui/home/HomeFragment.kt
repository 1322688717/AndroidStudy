package com.example.kotlin.ui.home

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentHomeBinding
import java.util.Random

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var homeViewModel: HomeViewModel? = null

    private var progressDialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initData()
        intiView()
        return root
    }

    private fun intiView() {
        _binding?.btnNews?.setOnClickListener(this)
        _binding?.btnCop?.setOnClickListener(this)
        _binding?.btnNext?.setOnClickListener(this)
    }

    private fun initData() {
        getSaoText()
    }

    private fun setProgressbar() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setCancelable(false)
        progressDialog?.setTitle("正在努力加载...")
        progressDialog?.show()
    }

    private fun getSaoText() {
        homeViewModel?.setSao()
        homeViewModel?.sao?.observe(
            viewLifecycleOwner,
            Observer {
                progressDialog?.dismiss()
                binding.tvSentenceContent.text = it
            },
        )
    }

    private fun copyStr(str: String) {
        try {
            // 获取剪贴版
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // 创建ClipData对象
            // 第一个参数只是一个标记，随便传入。
            // 第二个参数是要复制到剪贴版的内容
            val clip = ClipData.newPlainText("simple text", str)
            // 传入clipData对象.
            clipboard.setPrimaryClip(clip)
            ToastUtils.showShort("复制成功")
        } catch (e: Exception) {
            ToastUtils.showShort("复制失败")
        }
    }

    private fun showHighFingerCount() {
        val random = Random()
        val count = random.nextInt(50000) + 100
        if (count > 10000) {
            _binding?.tvSentenceCount?.text = "9999+"
        } else {
            _binding?.tvSentenceCount?.text = count.toString()
        }
    }

    private fun resetStar() {
        _binding?.ivSentence1?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star_press))
        _binding?.ivSentence2?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star_press))
        _binding?.ivSentence3?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star_press))
        _binding?.ivSentence4?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star_press))
        _binding?.ivSentence5?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star_press))
    }

    private fun showStar() {
        val startRandom = Random()
        val startCount = startRandom.nextInt(3) + 3
        if (startCount == 1) {
            _binding?.ivSentence2?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence3?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence4?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence5?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
        } else if (startCount == 2) {
            _binding?.ivSentence3?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence4?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence5?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
        } else if (startCount == 3) {
            _binding?.ivSentence4?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
            _binding?.ivSentence5?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
        } else if (startCount == 4) {
            _binding?.ivSentence5?.setImageDrawable(ResourceUtils.getDrawable(R.drawable.bjdzb_home_star))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            _binding?.btnNews?.id -> {
                val intent = Intent(activity, CommonImageActivity::class.java)
                startActivity(intent)
            }

            _binding?.btnNext?.id -> {
                showHighFingerCount()
                resetStar()
                showStar()
                getSaoText()
                setProgressbar()
            }

            _binding?.btnCop?.id -> {
                copyStr(_binding?.tvSentenceContent?.text.toString())
            }
        }
    }
}
