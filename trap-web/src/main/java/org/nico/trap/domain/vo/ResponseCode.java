package org.nico.trap.domain.vo;

public enum ResponseCode {

	SUCCESS("000000", "请求成功"),
	ERROR("500000", "请求成功"),
	
	;
	
	private String code;
	
	private String msg;

	private ResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public final String getCode() {
		return code;
	}

	public final void setCode(String code) {
		this.code = code;
	}

	public final String getMsg() {
		return msg;
	}

	public final void setMsg(String msg) {
		this.msg = msg;
	}
	
}
