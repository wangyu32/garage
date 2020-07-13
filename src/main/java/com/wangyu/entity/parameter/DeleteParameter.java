package com.wangyu.entity.parameter;

import lombok.Data;

/**
 * 删除参数
 * @author 	wangyu
 *
 */
@Data
public class DeleteParameter {
	
	/**
	 * 整型主键id的数组形式（主键为整型的情况使用）
	 */
	private Integer[] idArray;

	/**
	 * 字符串类型主键id的数组形式（主键为字符串类型的情况使用，如充值产品）
	 */
	private String[] strIdArray;

	/**
	 * 扩展属性1数组（有时候不仅仅需要传递ID属性，同时可能需传递其他属性，
	 * 并且与ID是一一对应的关系，此时使用长度相等的扩展属性1数组）
	 */
	private String[] ext1Array;

	/**
	 * 扩展属性2数组（有时候不仅仅需要传递ID属性，同时可能需传递其他属性，
	 * 并且与ID是一一对应的关系，此时使用长度相等的扩展属性2数组）
	 */
	private String[] ext2Array;
	

}
