package com.lanxum.jd.mobile.print.common.utils;


import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoadFile {

	// 下载文件路径
	private String downloadFilePath = null;
	// 保存文件路径
	private String saveFileDir = "";
	// 默认合理并发线程数
	private int threadCount = Runtime.getRuntime().availableProcessors() * 2;
	// 新文件名称
	private String newFileName = null;

	public DownLoadFile(int threadCount, String downloadFilePath,String newFileName,String saveFileDir) {
		// 用户指定线程数如果小于默认线程数则使用用户指定线程数
		if (threadCount < this.threadCount) {
			this.threadCount = threadCount;
		}
		this.downloadFilePath = downloadFilePath;
		this.saveFileDir = saveFileDir;
		this.newFileName = newFileName;
	}

	public String MulitThreadDownload() {

		// 数据合法性验证
		if (null == downloadFilePath || downloadFilePath.isEmpty()) {
			throw new RuntimeException("请指定下载路径!");
		}
		if (null == saveFileDir || saveFileDir.isEmpty()) {
			throw new RuntimeException("请指定保存路径!");
		}

		// 创建保存文件路径如果不存在
		File saveFileDirTemp = new File(saveFileDir);
		if (!saveFileDirTemp.exists()) {
			saveFileDirTemp.mkdirs();
		}

		// 处理文件名称
		if (null == newFileName || newFileName.isEmpty()) {
			newFileName = downloadFilePath.substring(downloadFilePath.lastIndexOf("/") + 1, downloadFilePath.length());
		}

		// 创建固定大小的线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
		try {
			// 根据文件长度计算合理线程数开始
			URLConnection urlConnection = new URL(downloadFilePath).openConnection();
			// 获取文件长度
			int downloadFileLength = urlConnection.getContentLength();
			// 计算每个线程负责的文件字节长度
			int averageThreadLength = downloadFileLength / threadCount;
			int residueThreadLength = downloadFileLength % threadCount;
			// 让每一个线程开始工作
			int startIndex = 0;
			int endIndex = 0;
			for (int i = 0; i < threadCount; i++) {

				// 计算每一个线程开始和计数索引
				startIndex = i * averageThreadLength;
				// 如果是最后一个线程，则将剩余的全部下载
				if ((i + 1) == threadCount) {
					endIndex = (i + 1) * averageThreadLength + residueThreadLength - 1;
				}
				endIndex = (i + 1) * averageThreadLength - 1;

				// 创建下载线程对象
				DownloadHandlerThread downloadHandlerThread = new DownloadHandlerThread();
				downloadHandlerThread.setDownloadFilePath(downloadFilePath);
				downloadHandlerThread.setSaveFilePath(saveFileDir + newFileName);
				downloadHandlerThread.setStartIndex(startIndex);
				downloadHandlerThread.setEndIndex(endIndex);
//				threadPool.execute(downloadHandlerThread);
				downloadHandlerThread.run();
			}
            return "success";
		} catch (Exception e) {
		    System.out.println("这里111");
			e.printStackTrace();
			return "error";
		} finally {
			// 关闭线程池
			threadPool.shutdown();
		}
	}
	
//	 public static void main(String[] args) {
//
//		    String downloadFilePath = "http://oapi-pan.jd.com/res/download/F2623D13ED87DB9ED42CD41886964E586711C63BF2A0AA32E59A2969DC435C5E2DF48E851D4E65C9?accessKey=1466&origin_uuid=f7338df8-27f9-4dfd-b5c5-85d88fbacc4a&origin_user=53782049-cea7-49cb-b8fc-58ed890a3089&appCode=JDDDMOBILE";
//		    DownLoadFile httpMulitThreadDownload = new DownLoadFile(1, downloadFilePath, "qqqqqq.pdf", "D:\\nginx\\html\\");
//			httpMulitThreadDownload.MulitThreadDownload();
//
//	}

}
