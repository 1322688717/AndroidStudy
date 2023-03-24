package com.java8888.java9999.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

/**
 * @ClassName: DraggingButton
 * @Author: CaoLong
 * @CreateDate: 2021/10/12 18:10
 * @Description:
 */
public class DraggingButton extends AppCompatButton {

    private int lastX = 0;
    private int lastY = 0;
    private int beginX = 0;
    private int beginY = 0;

    private int screenWidth = 720;
    private int screenHeight = 1280;

    public DraggingButton(Context context) {
        this(context, null);
    }

    public DraggingButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DraggingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();   // 触摸点与屏幕左边的距离
                lastY = (int) event.getRawY();   // 触摸点与屏幕上边的距离
                beginX = lastX;
                beginY = lastY;
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = (int) event.getRawX() - lastX;    // x轴拖动的绝对距离
                int dy = (int) event.getRawY() - lastY;    // y轴拖动的绝对距离

                // getLeft(): 子View的左边界到父View的左边界的距离, getRight():子View的右边界到父View的左边界的距离
                // 如下几个数据表示view应该在布局中的位置
                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                if (left < 0) {
                    left = 0;
                    right = left + getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + getHeight();
                }
                if (bottom > screenHeight) {
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                // 解决拖拽的时候松手点击事件触发
                if (Math.abs(lastX - beginX) < 10 && Math.abs(lastY - beginY) < 10) {
                    return super.onTouchEvent(event);
                } else {
                    setPressed(false);
                    return true;
                }
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
