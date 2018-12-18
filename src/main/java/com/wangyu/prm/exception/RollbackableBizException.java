package com.wangyu.prm.exception;

import java.io.Serializable;

/**
 * 可回滚异常，用于需要事务回滚的方法中抛出
 * @author 	wangyu wangyu@joygo.com 2016年10月13日
 *
 */
public class RollbackableBizException extends BizException implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 */
	public RollbackableBizException() {
		super();
	}

	/**
	 * 构造方法
	 * @param message - 错误信息
	 */
	public RollbackableBizException(String message) {
		super(message);
	}
	
	/**
	 * 构造方法
	 * @param cause - 异常
	 */
	public RollbackableBizException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造方法
	 * @param message - 错误信息
	 * @param cause - 异常
	 */
	public RollbackableBizException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
