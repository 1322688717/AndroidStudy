package com.example.baidusdk_application.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baidusdk_application.R;
import com.example.baidusdk_application.bean.CityResponse;
import com.example.mvplibrary.utils.CityUtil;

import java.util.List;

/**
 * 区/县列表适配器
 */
public class AreaAdapter  extends BaseQuickAdapter<CityUtil.AreaBean, BaseViewHolder> {
    public AreaAdapter(int layoutResId, @Nullable List<CityUtil.AreaBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityUtil.AreaBean item) {
        helper.setText(R.id.tv_city,item.name);//区/县的名称
        helper.addOnClickListener(R.id.item_city);//点击事件 点击之后得到区/县  然后查询天气数据
    }

}
