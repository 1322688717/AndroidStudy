package com.example.baidusdk_application.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baidusdk_application.R;
import com.example.baidusdk_application.bean.CityResponse;
import com.example.mvplibrary.utils.CityUtil;

import java.util.List;

/**
 * 市列表适配器
 */
public class CityAdapter extends BaseQuickAdapter<CityUtil.CityBean, BaseViewHolder> {
    public CityAdapter(int layoutResId, @Nullable List<CityUtil.CityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityUtil.CityBean item) {
        helper.setText(R.id.tv_city,item.name);//市名称
        helper.addOnClickListener(R.id.item_city);//点击事件  点击进入区/县列表
    }

}