package com.lanxum.jd.mobile.print.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiang
 */
public class DownloadHandlerThread extends  Thread {

    /**
     * 待下载的HTTP文件路径
     */
    private String downloadFilePath = null;
    /**
     * 下载保存文件路径
     */
    private String saveFilePath = null;
    /**
     * 文件随机写入开始索引
     */
    private int startIndex = 0;
    /**
     * 文件随机写入结束索引
     */
    private int endIndex = 0;

    @Override
    public void run() {

        System.out.println("线程名称[" + Thread.currentThread().getName() + "]于时间["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]开始下载....");

        // 文件输入流对象
        InputStream fileInputStream = null;
        // 随机访问文件
        RandomAccessFile randomAccessFile = null;

        // 获取一个URL打开链接对象
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(downloadFilePath).openConnection();
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(true);
//            urlConnection.setConnectTimeout(0);
//            urlConnection.setReadTimeout(0);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 设置该链接允许和用户交互
        assert urlConnection != null;
        urlConnection.setAllowUserInteraction(true);
        // 设置请求属性字节范围
        urlConnection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex + "");
        try {
            // 获取指定的文件流
            fileInputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ------------------------------------------------------------------------------获取随机文件流结束

        // 写文件流到指定的文件开始
        try {
            // 创建文件随机访问对象
            randomAccessFile = new RandomAccessFile(saveFilePath, "rw");
            // 将文件写入位置移动到其实点
            randomAccessFile.seek(startIndex);
            // 写入文件
            int bytes = 0;
            byte[] buffer = new byte[100 * 1024];
            assert fileInputStream != null;
            while ((bytes = fileInputStream.read(buffer, 0, buffer.length)) != -1) {
                randomAccessFile.write(buffer, 0, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != randomAccessFile) {
                    randomAccessFile.close();
                }
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // ------------------------------------------------------------------------写文件流到指定的文件结束

        System.out.println("线程名称[" + Thread.currentThread().getName() + "]于时间["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]下载完成！");
    }


    void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

}
