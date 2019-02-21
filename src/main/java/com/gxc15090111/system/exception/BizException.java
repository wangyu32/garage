package com.gxc15090111.system.exception;

import java.io.Serializable;

/**
 * 业务异常，用于事务不需要回滚的方法
 * @author 	gxc15090111  2018年10月13日
 *
 */
public class BizException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 */
	public BizException() {
		super();
	}

	/**
	 * 构造方法
	 * @param message - 错误信息
	 */
	public BizException(String message) {
		super(message);
	}

	/**
	 * 构造方法
	 * @param cause - 异常
	 */
	public BizException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造方法
	 * @param message - 错误信息
	 * @param cause - 异常
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
	}


}
