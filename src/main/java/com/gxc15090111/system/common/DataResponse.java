package com.gxc15090111.system.common;

/**
 * 微服务返回信息Object类型数据
 * @author 	wangyu wangyu@joygo.com 2016年10月13日
 *
 */
public class DataResponse extends BaseResponse {
	
	/**
	 * 微服务返回业务数据-Object类型
	 */
	private Object data;

	public DataResponse() {
		super();
	}

	public DataResponse(Object data) {
		this.data = data;
	}

	public DataResponse(String code) {
		super(code);
	}

	public DataResponse(String code, String message) {
		super(code, message);
	}

	public DataResponse(String code, String message, Object data) {
		super(code, message);
		this.data = data;
	}

	public DataResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}
}
