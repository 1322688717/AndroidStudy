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


    }
}