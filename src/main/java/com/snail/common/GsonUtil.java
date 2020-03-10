package com.snail.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author zhaofh 2016年10月20日
 * Gson使用工具类
 */

public class GsonUtil {
	private static Gson gson = new Gson();
	
	/**
	 * 将obj转为JSON
	 * @author zhaofh 2016年10月20日 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	/***
	 * 格式化json时间
	 * @param obj
	 * @return
	 */
	public static String toJsonFormatter(Object obj) {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}
	
	public static String toJsonFormatterDate(Object obj) {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(obj);
	}
	
	public static String toJsonFormatterNull(Object obj) {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();
		return gson.toJson(obj);
	}
	
	public static String toJsonFormatterTime(Object obj) {
		gson = new GsonBuilder().setDateFormat("HH:mm:ss").create();
		return gson.toJson(obj);
	}
}
