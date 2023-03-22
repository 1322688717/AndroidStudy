package com.example.kotlin.ui.dashboard

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin.databinding.ActivityWallPaperBinding
import com.gyf.immersionbar.ImmersionBar
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WallPaperActivity : AppCompatActivity() {
    private var binding: ActivityWallPaperBinding? = null
    private var img: String? = null
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallPaperBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .init()
        img = intent.getStringExtra("img")
        Glide.with(this)
            .load(img)
            .into(binding!!.imgPicture)
        bitmap = getBitMap(img)
        initView()
    }

    private fun initView() {
        binding!!.btnDownWallpaper.setOnClickListener {
            btnSavePicture()
        }
    }

    private fun btnSavePicture() {
//        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        // val bitmap = BitmapFactory.decodeResource(resources, binding!!.imgPicture.id)

// 1. 找一个可用的文件位置
        val mediaDir = externalMediaDirs
        var output_dir: File? = null
        output_dir = if (mediaDir != null) {
            mediaDir[0]
        } else {
            applicationContext.filesDir
        }

        // 2. 创建一个空文件
        val file_name = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.CHINA).format(
            System.currentTimeMillis(),
        ) + ".jpg"
        val f = File(output_dir, file_name)

// 3. 保存图片 （替换成你的图）
        var os: OutputStream? = null
        try {
            os = FileOutputStream(f)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, os)
        // 4. 让相册扫描这个图片
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sendBroadcast(Intent(Camera.ACTION_NEW_PICTURE, Uri.fromFile(f)))
            Toast.makeText(applicationContext, "我起作用了", Toast.LENGTH_SHORT).show()
        }
        val mimeType = MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(f.toString())
        MediaScannerConnection.scanFile(
            applicationContext,
            arrayOf(f.absolutePath),
            arrayOf(mimeType),
        ) { s, uri -> Toast.makeText(applicationContext, "已保存", Toast.LENGTH_SHORT).show() }
    }

    /**
     * Url转Bitmap
     *
     * @param url
     * @return
     */
    fun getBitMap(url: String?): Bitmap? {
        // 新启一个线程进行转换
        Thread(
            Runnable {
                var imageUrl: URL? = null
                try {
                    imageUrl = URL(url)
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                }
                try {
                    val conn = imageUrl!!.openConnection() as HttpURLConnection
                    conn.doInput = true
                    conn.connect()
                    val inputStream = conn.inputStream
                    bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            },
        ).start()
        return bitmap
    }
}
