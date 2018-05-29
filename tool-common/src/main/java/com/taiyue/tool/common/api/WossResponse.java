package com.taiyue.tool.common.api;

import com.taiyue.tool.common.DateUtils;

import java.util.Date;

public class WossResponse<T> {
	private T respBody;

	private String code;

	private String message;

	private Date lastFetchTime;

	public WossResponse() {
		this(null, null, null);
	}

	public WossResponse(T respBody) {
		this(respBody, null, null);
	}

	public WossResponse(BusinessCode code) {
		this(null, code.getCode(), code.getMsg());
	}

	public WossResponse(String code, String message) {
		this(null, code, message);
	}

	public WossResponse(T respBody, BusinessCode code) {
		this(respBody, code.getCode(), code.getMsg());
	}

	public WossResponse(T respBody, String code, String message) {
		this.respBody = respBody;
		this.code = code;
		this.message = message;
		this.lastFetchTime = DateUtils.getNowDate();
	}

	public T getRespBody() {
		return respBody;
	}

	public void setRespBody(T respBody) {
		this.respBody = respBody;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getLastFetchTime() {
		return lastFetchTime;
	}

	public void setLastFetchTime(Date lastFetchTime) {
		this.lastFetchTime = lastFetchTime;
	}

	public void setBusinessCode(BusinessCode code) {
		this.code = code.getCode();
		this.message = code.getMsg();
	}
}