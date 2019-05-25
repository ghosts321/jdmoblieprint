/**
 * 
 */
package com.lanxum.jd.mobile.print.common.utils;

import java.util.Random;

/**
 * US工具类
 * @author jingwei
 *
 */
public class USUtil {
	/**
	 * 长度
	 */
	public static final int US_LENGTH = 16;
	
	/**
	 * 包含字符
	 */
	public static final String US_CHARACTER = "abcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * 私有构造方法，禁止对该类进行实例化
	 */
	private USUtil() {
		
	}
	
	/**
	 * 返回US码
	 * @return
	 */
	public static String getUS() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < US_LENGTH; i++) {     
            int number = random.nextInt(US_CHARACTER.length());     
            sb.append(US_CHARACTER.charAt(number));     
        }     
        
        return sb.toString();   
	}
}
