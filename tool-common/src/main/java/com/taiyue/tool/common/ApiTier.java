package com.taiyue.tool.common;

public enum ApiTier {
	CONTROLLER(1, "URL"), SERVICE(2, "SERVICE"), DAO(3, "DAO");
	private String tier;
	private Integer code;

	private ApiTier(Integer code, String tier) {
		this.tier = tier;
		this.code = code;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
