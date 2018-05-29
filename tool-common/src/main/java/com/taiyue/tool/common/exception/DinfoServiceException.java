/*  
 * project tool-process
 * fileName ServiceException.java 
 * package com.taiyue.tool.process.exception
 * description TODO(用一句话描述该文件做什么) 
 * copyright © 2017 www.99114.com Inc. All rights reserved.	
 */
package com.taiyue.tool.common.exception;

import com.taiyue.tool.common.api.BusinessCode;
import org.springframework.http.HttpStatus;

/**
 * description: TODO(这里用一句话描述这个类的作用) <br>
 * date: 2017年1月21日 下午5:47:34 <br>
 * 
 * @author: ljb
 * @since: v1.0.0
 * @version v1.0.0
 */
public class DinfoServiceException extends RuntimeException {
	/**
	 * 上午11:46:25description
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private String code;
	private String message;

	public DinfoServiceException(Throwable ex) {
		super(ex);
	}

	public DinfoServiceException(RuntimeException ex) {
		super(ex);
	}

	public DinfoServiceException(Throwable ex, BusinessCode code) {
		super(ex);
		this.code = code.getCode();
		this.message = code.getMsg();
	}

	public DinfoServiceException(RuntimeException ex, BusinessCode code) {
		super(ex);
		this.code = code.getCode();
		this.message = code.getMsg();
	}

	public DinfoServiceException(Throwable ex, HttpStatus httpStatus) {
		super(ex);
		this.httpStatus = httpStatus;
	}

	public DinfoServiceException(RuntimeException ex, HttpStatus httpStatus) {
		super(ex);
		this.httpStatus = httpStatus;
	}

	public DinfoServiceException(Throwable ex, HttpStatus httpStatus, BusinessCode code) {
		super(ex);
		this.httpStatus = httpStatus;
		this.code = code.getCode();
		this.message = code.getMsg();
	}

	public DinfoServiceException(RuntimeException ex, HttpStatus httpStatus, BusinessCode code) {
		super(ex);
		this.httpStatus = httpStatus;
		this.code = code.getCode();
		this.message = code.getMsg();
	}

	public DinfoServiceException(Throwable ex, HttpStatus httpStatus, String code, String message) {
		super(ex);
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public DinfoServiceException(RuntimeException ex, HttpStatus httpStatus, String code, String message) {
		super(ex);
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setBusinessCode(BusinessCode businessCode) {
		this.code = businessCode.getCode();
		this.message = businessCode.getMsg();
	}
}