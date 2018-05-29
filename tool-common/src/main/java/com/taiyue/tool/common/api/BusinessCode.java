package com.taiyue.tool.common.api;

public enum BusinessCode {
	SUCCESS("0000", "操作成功"), FAIL("0001", "操作失败"),CHECKFAIL("0002", "校验失败");

	private String code;
	private String msg;

	private BusinessCode(BusinessCode msg) {
		this.code = msg.getCode();
		this.msg = msg.getMsg();
	}

	private BusinessCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}