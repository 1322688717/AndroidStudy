package com.example.baidusdk_application.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baidusdk_application.R;
import com.example.baidusdk_application.bean.CityResponse;
import com.example.mvplibrary.utils.CityUtil;

import java.util.List;

/**
 * 省列表适配器
 */
public class ProvinceAdapter extends BaseQuickAdapter<CityUtil.ProvinceBean, BaseViewHolder> {
    public ProvinceAdapter(int layoutResId, @Nullable List<CityUtil.ProvinceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityUtil.ProvinceBean item) {
        helper.setText(R.id.tv_city,item.name);//省名称
        helper.addOnClickListener(R.id.item_city);//点击之后进入市级列表
    }


}
