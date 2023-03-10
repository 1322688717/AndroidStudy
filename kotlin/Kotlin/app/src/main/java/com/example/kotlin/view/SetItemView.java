package com.example.kotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.kotlin.R;

/**
 * @author zhaichaoqun
 * @time 2023/3/7
 */

public class SetItemView extends ConstraintLayout {

    private ImageView mIvIcon;
    private ImageView mIvSubIcon;
    private ImageView mIvTipIcon;
    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private View mBottomLine;

    public SetItemView(@NonNull Context context) {
        super(context);
    }

    public SetItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SetItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SetItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context,AttributeSet attributeSet){
        LayoutInflater.from(context).inflate(R.layout.set_item_view,this,true);
        mIvIcon = findViewById(R.id.iv_icon);
        mTvTitle = findViewById(R.id.tv_title);
        mTvSubTitle = findViewById(R.id.tv_subtitle);
        mBottomLine = findViewById(R.id.bottom_line);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SettingItemView);
        //标题
        String title = typedArray.getString(R.styleable.SettingItemView_title);
        if (mTvTitle != null) {
            mTvTitle.setText(title);
            int titleColor = typedArray.getColor(R.styleable.SettingItemView_titleColor, getResources().getColor(R.color.secondColorBlack));
            mTvTitle.setTextColor(titleColor);
        }

        //图标
        int iconResourceId = typedArray.getResourceId(R.styleable.SettingItemView_icon, 0);
        if (mIvIcon != null) {
            if (iconResourceId != 0) {
                mIvIcon.setImageResource(iconResourceId);
                mIvIcon.setVisibility(VISIBLE);
            } else {
                mIvIcon.setVisibility(GONE);
            }
        }

        int rippleBgResourceId = typedArray.getResourceId(R.styleable.SettingItemView_rippleBg, 0);
        if (rippleBgResourceId != 0) {
            setBackgroundResource(rippleBgResourceId);
        }

        boolean showBottomLine = typedArray.getBoolean(R.styleable.SettingItemView_showBottomLine, false);
        mBottomLine.setVisibility(showBottomLine ? VISIBLE : INVISIBLE);

    }
}
