package com.eureka.doumi.utils;

public class ResponseApi<T> {
	
	/**
	   *  返回成功模板
	 * @param retCode
	 * @param retMsg
	 * @param data
	 * @return
	 */
	

	public <T> Response<T> setSuccess(Integer retCode, String retMsg, T data){
		return new Response<T>(retCode,retMsg,data);
	}
	
	public <T> Response<T> setSuccess(){
		return setSuccess(ReturnResult.SUCCESS.getReturnCode(),ReturnResult.SUCCESS.getReturnMessage(),null);
	}
	
	public <T> Response<T> setSuccess(T data){
		return setSuccess(ReturnResult.SUCCESS.getReturnCode(),ReturnResult.SUCCESS.getReturnMessage(),data);
	}
	
	
	
	/**
	   * 返回失败模板
	 * @param retCode
	 * @param retMsg
	 * @param data
	 * @return
	 */
	public <T> Response<T> setError(Integer retCode, String retMsg, T data){
		return new Response<T>(retCode,retMsg,data);
	}
	
	public <T> Response<T> setError(){
		return setError(ReturnResult.ERROR.getReturnCode(), ReturnResult.ERROR.getReturnMessage(), null);
	}
	
	public <T> Response<T> setError(T data){
		return setError(ReturnResult.ERROR.getReturnCode(), ReturnResult.ERROR.getReturnMessage(), data);
	}
	
	
	/**
	   * 访问量过大，返回等待模板
	 * @param retCode
	 * @param retMsg
	 * @param data
	 * @return
	 */
	public <T> Response<T> setWait(Integer retCode, String retMsg, T data){
		return new Response<T>(retCode,retMsg,data);
	}
	
	public <T> Response<T> setWait(){
		return setWait(ReturnResult.WAIT.getReturnCode(), ReturnResult.WAIT.getReturnMessage(), null);
	}
	
	public <T> Response<T> setWait(T data){
		return setWait(ReturnResult.WAIT.getReturnCode(), ReturnResult.WAIT.getReturnMessage(), data);
	}
	
	
	
}
