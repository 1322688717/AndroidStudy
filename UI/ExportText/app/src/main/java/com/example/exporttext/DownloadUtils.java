package com.example.exporttext;

import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;

public class DownloadUtils {

    /**
     * 将字符串写入到.txt文件中
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeRootDirectory(filePath);

        String strFilePath = filePath+fileName;
        try {
            File file = new File(strFilePath);
            if (!file.exists() && file.getParentFile() != null) {
                boolean isMake = file.getParentFile().mkdirs();
                boolean isCreate = file.createNewFile();
                Log.d("========", "writeTxtToFile: " + isMake + isCreate);
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strcontent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                boolean isMkdir = file.mkdir();
                // 返回值是false  因为没有SD卡权限
                Log.d("========", "writeTxtToFile: " + isMkdir);
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}

