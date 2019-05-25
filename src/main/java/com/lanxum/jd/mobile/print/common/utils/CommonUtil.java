package com.lanxum.jd.mobile.print.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

	//判断字符串是否仅为数字:
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
	  return true;
	}
	
	public static String Printer(String filePath){
        File file = new File(filePath);
        //构建打印请求属性集
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        //设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        //定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //构造待打印的文件流
        InputStream fis = null;
        if(defaultService != null) {
            try {
                //创建打印作业
                DocPrintJob job = defaultService.createPrintJob();
                fis = new FileInputStream(file);
                DocAttributeSet das = new HashDocAttributeSet();
                //指定打印内容
                Doc doc = new SimpleDoc(fis, flavor, das);
                job.print(doc, pras);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
        }else{
        	 return "error";
        }
	}
}