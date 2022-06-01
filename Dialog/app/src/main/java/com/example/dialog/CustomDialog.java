package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dialog.databinding.ActivityCustomDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CustomDialog extends AppCompatActivity {

    ActivityCustomDialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnShowdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiyDialog2();
            }
        });

//        BottomSheetDialog

        binding.btnBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog();
            }
        });
    }

    /**
     * 底部dialog
     */
    private void bottomDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CustomDialog.this);
        bottomSheetDialog.setContentView(R.layout.dialog_1);
        bottomSheetDialog.show();
    }

    /**
     * 自定义dialog2 简单自定义布局
     */
    private void DiyDialog2() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(CustomDialog.this);
        alterDiaglog.setView(R.layout.dialog_1);//加载进去
        AlertDialog dialog = alterDiaglog.create();
        dialog.show();

//        //放在show()之后，不然有些属性是没有效果的，比如height和width
//        Window dialogWindow = dialog.getWindow();
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        // 设置高度和宽度
//        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.6
//        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
//
//        //p.gravity = Gravity.CENTER;//设置位置
//
//        p.alpha = 0.8f;//设置透明度
//        dialogWindow.setAttributes(p);


    }
}