package com.lanxum.jd.mobile.print.common.base.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lanxum.jd.mobile.print.common.morpher.DateMorpherEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;


/**
 * 基础Controller类
 * @author jingwei
 *
 */
public abstract class BaseController {
	
	/**
	 * log4j日志对象
	 */
	protected static Logger logger = null;
	
	protected final static String DATE_FORMATE = "yyyy-MM-dd";
	
	/**
	 * 返回jsonMap对象
	 */
	protected Map<String, Object> jsonMap = new HashMap<String, Object>();
	
	static{
		/*
		 * 处理json转java日期问题
		 */
		String[] dateFormats = new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy-MM-dd HH:mm:ss SSS",
				"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy/MM/dd HH:mm:ss SSS",
				"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy.MM.dd HH:mm:ss SSS"};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherEx(dateFormats, (Date) null));
	}
	
	/**
	 * 构造方法
	 */
	public BaseController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * @return the jsonMap
	 */
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	/**
	 * @param jsonMap the jsonMap to set
	 */
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	/**
	 * 返回服务端处理结果
	 * @param obj 服务端输出对象
	 * @return 输出处理结果给前段JSON格式数据
	 * @author hjw
	 * @since 2016-12-20
	 */
	public String responseResult(Object obj) {
		JSONObject jsonObj = null;
//		if (obj != null) {
//			logger.info("后端返回对象：{}", obj);
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//			jsonObj = JSONObject.fromObject(obj, jsonConfig);
//			logger.info("后端返回数据：" + jsonObj);
//			
//			if (Constant.SERVICE_RESPONSE_SUCCESS_CODE.equals(jsonObj.getString(Constant.SERVICE_RESPONSE_RESULT_FLAG))) {
//				jsonObj.element(Constant.RESPONSE_RESULT_FLAG_ISERROR, false);
//				jsonObj.element(Constant.SERVICE_RESPONSE_RESULT_MSG, "");
//			} else {
//				jsonObj.element(Constant.RESPONSE_RESULT_FLAG_ISERROR, true);
//				String errMsg = jsonObj.getString(Constant.SERVICE_RESPONSE_RESULT_MSG);
//				jsonObj.element(Constant.SERVICE_RESPONSE_RESULT_MSG, errMsg == null ? Constant.SERVICE_RESPONSE_NULL : errMsg);
//			}
//		}
		logger.info("输出结果：{}", jsonObj.toString());
		
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * @param obj 输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseSuccess(Object obj) {
		JSONObject jsonObj = null;
//		if (obj != null) {
//			logger.info("后端返回对象：{}", obj);
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//			jsonObj = JSONObject.fromObject(obj, jsonConfig);
//			logger.info("后端返回数据：" + jsonObj);
//			
//			jsonObj.element(Constant.RESPONSE_RESULT_FLAG_ISERROR, false);
//			jsonObj.element(Constant.SERVICE_RESPONSE_RESULT_MSG, "");
//		}
		logger.info("输出结果：{}", jsonObj.toString());
		
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * @param obj 输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseArraySuccess(Object obj) {
		JSONArray jsonObj = null;
//		if (obj != null) {
//			logger.info("后端返回对象：{}", obj);
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class,
//					new JsonDateValueProcessor());
//			jsonObj = JSONArray.fromObject(obj, jsonConfig);
//			logger.info("后端返回数据：" + jsonObj);
//		}
		logger.info("输出结果：{}", jsonObj.toString());
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * @param obj 输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseSuccess(Object obj, String msg) {
		JSONObject jsonObj = null;
//		if (obj != null) {
//			logger.info("后端返回对象：{}", obj);
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//			jsonObj = JSONObject.fromObject(obj, jsonConfig);
//			logger.info("后端返回数据：" + jsonObj);
//			
//			jsonObj.element(Constant.RESPONSE_RESULT_FLAG_ISERROR, false);
//			jsonObj.element(Constant.SERVICE_RESPONSE_RESULT_MSG, msg);
//		}
		logger.info("输出结果：{}", jsonObj.toString());
		
		return jsonObj.toString();
	}

	/**
	 * 返回失败
	 * @param errorMsg 错误信息
	 * @return 输出失败的JSON格式数据
	 */
	public String responseFail(String errorMsg) {
		JSONObject jsonObj = new JSONObject();
//		jsonObj.put(Constant.RESPONSE_RESULT_FLAG_ISERROR, true);
//		jsonObj.put(Constant.SERVICE_RESPONSE_RESULT_MSG, errorMsg);
		logger.info("输出结果：{}", jsonObj.toString());
		
		return jsonObj.toString();
	}
}
