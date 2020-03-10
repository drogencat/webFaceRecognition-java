package com.snail.common;

import java.util.List;
import java.util.Map;

/**
 * 返回数据DTO
 * @author zhaofh 2016年7月11日
 *
 */
public class ResultDto {
	//返回结果信息 默认失败falid成功success
	private String result="falied";
	//返回结果编码 默认失败0成功1
	private int resultCode = 0;
	//返回结果：对象
	private Map<Object,Object> data;
	//返回结果：集合
	private List<Map<Object,Object>> dataList;
	//返回结果：对象集合
	private Map<Object,List<?>> mapList;
	//返回结果：对象pojo
	private Object object;
	
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Map<Object, List<?>> getMapList() {
		return mapList;
	}
	public void setMapList(Map<Object, List<?>> mapList) {
		this.mapList = mapList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Map<Object, Object> getData() {
		return data;
	}
	public void setData(Map<Object, Object> data) {
		this.data = data;
	}
	public List<Map<Object, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<Object, Object>> dataList) {
		this.dataList = dataList;
	}
}
