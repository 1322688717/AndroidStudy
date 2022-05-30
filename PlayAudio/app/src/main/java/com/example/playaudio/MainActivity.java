package com.example.playaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.playaudio.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String url= "https://palmmob3.oss-cn-shenzhen.aliyuncs.com/palmmob3_dev/user_storage/51/1286/1653646318820-1653646324.wav";
    MediaPlayer mediaPlayer;
    private boolean isRelease = true;   //判断是否MediaPlayer是否释放的标志
    //private MediaPlayer mPlayer = null;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = new MediaPlayer();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mp.setDataSource(url);
                    mp.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
            }
        });

        binding.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.pause();
            }
        });

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                mp.release();
            }
        });


    }
}