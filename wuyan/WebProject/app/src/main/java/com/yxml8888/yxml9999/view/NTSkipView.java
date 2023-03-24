package com.yxml8888.yxml9999.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 跳过按钮（可设置是否处理点击事件）
 * <p>
 * Created by Firry.
 */
public class NTSkipView extends AppCompatTextView {
    private boolean isAcceptAction = true;

    public NTSkipView(Context context) {
        super(context);
    }

    public NTSkipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NTSkipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isAcceptAction) {
            return isAcceptAction;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}

