package com.eureka.doumi.utils;

public enum ReturnResult {
	
	SUCCESS(200,"success"),ERROR(500,"error"),WAIT(101,"waiting......");
	
	private Integer returnCode;
	
	private String returnMessage;

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

	private ReturnResult(Integer returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
	
	
}
