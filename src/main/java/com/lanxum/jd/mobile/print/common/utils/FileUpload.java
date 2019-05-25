package com.lanxum.jd.mobile.print.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	/**
	 * @return
	 * @throws IOException 
	 */
	public String excelImport(
			String filePath,
			MultipartFile  excelFile,HttpServletRequest request) throws IOException{
		if (excelFile != null){
			String filename=excelFile.getOriginalFilename(); 
			//String a=request.getRealPath("u/cms/www/201509");
			SaveFileFromInputStream(excelFile.getInputStream(),request.getRealPath("u/cms/www/201509"),filename);//保存到服务器的路径
		}
		return "";
	}
	
	/**
	 * 将MultipartFile转化为file并保存到服务器上的某地
	 */
	public void SaveFileFromInputStream(InputStream stream,String path,String savefile) throws IOException
	{       
        FileOutputStream fs=new FileOutputStream( path + "/"+ savefile);
        System.out.println("------------"+path + "/"+ savefile);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread=stream.read(buffer))!=-1)
        {
            bytesum+=byteread;
            fs.write(buffer,0,byteread);
            fs.flush();
        } 
        fs.close();
        stream.close();
	}
}
