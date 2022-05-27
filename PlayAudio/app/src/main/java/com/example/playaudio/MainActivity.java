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

    String url= "http://meimeibucket.oss-cn-shanghai.aliyuncs.com/1512565590_86.mp3?OSSAccessKeyId=LTAIhL8GLNt0yDff&Expires=1537590893&Signature=EjC9TM1%2BUkzKScCAYDxEK1Gf3Yg%3D";
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Uri uri = Uri.parse(url);


        try {

            mediaPlayer.setDataSource(MainActivity.this,uri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.e("MediaPlayer ","开始播放");
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });



    }
}