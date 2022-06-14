package com.example.baidusdk_application.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baidusdk_application.R;
import com.example.baidusdk_application.bean.FutureWeatherResponse;

import java.util.List;

public class FutureWeatherAdapter extends BaseQuickAdapter<FutureWeatherResponse.DailyDTO, BaseViewHolder> {

    public FutureWeatherAdapter(int layoutResId, @Nullable List<FutureWeatherResponse.DailyDTO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FutureWeatherResponse.DailyDTO item) {
        helper.setText(R.id.tv_data_rc,item.getFxDate());
        helper.setText(R.id.tv_weather_rc,item.getTextDay());
        helper.setText(R.id.tv_temperature_max_rc,item.getTempMax()+"℃");
        helper.setText(R.id.tv_temperature_low_rc,item.getTempMin()+"℃");
    }
}
