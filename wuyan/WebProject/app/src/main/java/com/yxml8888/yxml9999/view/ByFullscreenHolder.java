package com.yxml8888.yxml9999.view;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class ByFullscreenHolder extends FrameLayout {

    public ByFullscreenHolder(Context context) {
        super(context);
        setBackgroundColor(context.getResources().getColor(android.R.color.black));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
