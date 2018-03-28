package com.mmc.sampletest.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by 上海滩小马哥 on 2018/03/28.
 */

public class FileHelper {

    public FileHelper(Context context) {
    }

    public static void saveData(String fileName, String data) {

        OutputStreamWriter n = null;
        try {
            n = new OutputStreamWriter(new FileOutputStream(fileName, true));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }// OutputStreamWriter 是字节流通向字符流的桥梁
        BufferedWriter s = new BufferedWriter(n);// 将文本写入字符输出流
        try {
            s.write(data + "\n");// 写入字符
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
        } catch (IOException a) {
            System.out.println("写入数据失败");
        } finally {
            try {
                s.flush();
                s.close();
                n.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isFolderExists(String folder) {
        File file = new File(folder);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static void saveMapData(String content){
        saveData(getMapDataPath(), content);
    }

    public static String getMapDataPath() {
        File file = null;
        try {
            String folder = Environment.getExternalStorageDirectory() + "/locationData";
            isFolderExists(folder);
            file = new File(folder + "/mapData.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return file.getPath();
    }
}
