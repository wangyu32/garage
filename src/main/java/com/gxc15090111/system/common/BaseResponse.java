package com.gxc15090111.system.common;

/**
 * 微服务返回信息父类
 * @author 	gxc15090111  2018年10月13日 下午5:18:08
 *
 */
public class BaseResponse {
	
	/**
	 * 响应码
	 */
	private String code = Code.SUCCESS;
	
	/**
	 * 响应信息
	 */
	private String message = "";

	public BaseResponse() {
	}

	public BaseResponse(String message) {
		this.message = message;
	}

	public BaseResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public BaseResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
