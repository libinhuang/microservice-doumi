package com.eureka.doumi.utils;
/**
 * 返回实体数据
 * @author Administrator
 *
 * @param <T>
 */
public class Response<T> {
	
	private Integer returnCode;
	
	private String returnMessage;
	
	private T data;
	
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public Response() {
		
	}
	
	public Response(Integer returnCode, String returnMessage, T data) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
		this.data = data;
	}
	
	
	
	
}
