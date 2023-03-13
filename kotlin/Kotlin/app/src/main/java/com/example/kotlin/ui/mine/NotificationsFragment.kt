package com.example.kotlin.ui.mine

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.databinding.FragmentNotificationsBinding
import com.example.kotlin.view.SetItemView


class NotificationsFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private var mItemMore: SetItemView? = null

    private var mItemSuggestion: SetItemView? = null

    private var mItemUserProtocol: SetItemView? = null

    private var mItemPrivacyPolicy: SetItemView? = null

    private var mItemAboutUs: SetItemView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        initData()
        initView()
        return binding.root
    }

    private fun initView() {
        binding.itemMeBottomItem.itemMore.setOnClickListener(this)
        binding.itemMeBottomItem.itemSuggestion.setOnClickListener(this)
        binding.itemMeBottomItem.itemUserProtocol.setOnClickListener(this)
        binding.itemMeBottomItem.itemPrivacyPolicy.setOnClickListener(this)
        binding.itemMeBottomItem.itemAboutUs.setOnClickListener(this)
    }

    private fun initData() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.itemMeBottomItem.itemMore.id -> {
                var intent = Intent(activity, SettingsActivity::class.java)
                startActivity(intent)
            }

            binding.itemMeBottomItem.itemSuggestion.id -> {
                var intent = Intent(activity, SettingsActivity::class.java)
                startActivity(intent)
            }

            binding.itemMeBottomItem.itemUserProtocol.id -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                val data = Uri.parse("http://www.baidu.com")
                intent.data = data
                startActivity(intent)
            }

            binding.itemMeBottomItem.itemPrivacyPolicy.id -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                val data = Uri.parse("https://www.google.com/")
                intent.data = data
                startActivity(intent)
            }

            binding.itemMeBottomItem.itemAboutUs.id -> {
                var intent = Intent(activity, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
