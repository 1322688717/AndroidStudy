package com.example.networkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.networkimage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String name;
    int age;
    String dress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load("https://thirdwx.qlogo.cn/mmopen/vi_32/EaictvyqYMsibhrdDciaLVs460Sdn3m5r3Lpb60zHyiaIc2HicEU3pbgsXwr1iavWB1ibeP43ExnzARe46lTrVGPv4RSg/132")
                .into(binding.imageView);

        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
                showToast("12314685");
            }
        });
    }

    /**
     * 解决Toast重复显示的问题
     */
    private Toast mToast;
    public void showToast(String text) {
        if(mToast == null) {
            mToast = Toast.makeText(MainActivity.this, text,Toast.LENGTH_SHORT);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(MainActivity.this, text,Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

}