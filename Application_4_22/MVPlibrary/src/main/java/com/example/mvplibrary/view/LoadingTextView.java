package com.example.mvplibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LoadingTextView extends AppCompatTextView {
    private LinearGradient mlinearGradient;
    private Paint mPaint;
    private Matrix mMatrix;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    private boolean mAnimating = true;

    public LoadingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0){
                mPaint = getPaint();
                mlinearGradient = new LinearGradient(-mViewWidth,0,0,0,
                        new int[]{0x33ffffff, 0xff3286ED, 0x33ffffff},
                        new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
                mPaint.setShader(mlinearGradient);
                mMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimating&&mMatrix!=null){
            mTranslate+=mViewWidth/10;
            if (mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate,0);
            mlinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(50);
        }
    }
}
