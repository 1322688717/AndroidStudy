package com.example.bottomsheetdialog.BottomSheetDialogFragment;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bottomsheetdialog.R;
import com.example.bottomsheetdialog.databinding.ActivityBottomSheetDialogFragmentBinding;

public class BottomSheetDialogFragmentActivity extends AppCompatActivity {
    ActivityBottomSheetDialogFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomSheetDialogFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialog myBottomSheetDialog = new MyBottomSheetDialog();
                myBottomSheetDialog.show(getSupportFragmentManager(),"MyBottomSheetDialog");
            }
        });
    }
}