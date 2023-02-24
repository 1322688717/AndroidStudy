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


        binding.btnBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog();
            }
        });

        binding.btnDialogCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3();
            }
        });
    }

    private void dialog3() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(CustomDialog.this,R.style.MyDialog);
        alterDiaglog.setView(R.layout.dialog_3);
        AlertDialog dialog = alterDiaglog.create();
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        // 设置高度和宽度
        p.height = (int) (d.getHeight());
         p.width = (int) (d.getWidth());
        p.gravity = Gravity.TOP;
//         p.alpha = 0.8f;
        dialogWindow.setAttributes(p);
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
        alterDiaglog.setView(R.layout.dialog_1);
        AlertDialog dialog = alterDiaglog.create();
        dialog.show();


    }
}