package com.lanxum.jd.mobile.print;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.lanxum.jd.mobile.print.common.utils.OfficeToPDFUtils;
import com.lanxum.jd.mobile.print.modules.busprintpdf.controller.BusPrintPdfController;



@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "com.lanxum.jd.mobile.print.*" })  
public class MobileprintApplicationTests {


//    @Autowired
//	private OfficeToPDFUtils officeToPDFUtils;
    
	@Autowired
	private BusPrintPdfController busPrintPdfController;

    @Test
	public void test() throws Exception {
    	String token = "zH3FgfzKko1MflWE5VkQiDJw8YwUcHuVsIKC1LylQCM=";
    	String fileUrl ="http://oapi-pan.jd.com/res/download/D4C5FCE381B00D9B3667BDC77082FAD218F8AB65618E642E766422640FB27B3CABDF9D29D32D08E2?accessKey=df66&origin_uuid=c7e02439-61a9-4784-957a-244ce812620e&origin_user=00f45ac0-d445-4964-84fa-48d93df0247f&appCode=JDDDMOBILE";
    	String fileName ="d402cef2.jpg";
    	String extName =".jpg";
		String duplex ="0";
		String copies ="1";
		String pageRangeStart ="1";
		String pageRangeEnd ="2";
//    	busPrintPdfController.printPdf(token, fileUrl, fileName, extName, duplex, copies, pageRangeStart, pageRangeEnd);
    }
}
