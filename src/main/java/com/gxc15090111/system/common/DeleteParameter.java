package com.gxc15090111.system.common;

/**
 * 删除参数
 * @author 	wangyu@joygo.com 2016年11月18日 下午23:22:34
 *
 */
public class DeleteParameter {
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;
	
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
	
	public Integer getRef_p_id() {
		return ref_p_id;
	}

	public void setRef_p_id(Integer ref_p_id) {
		this.ref_p_id = ref_p_id;
	}

	public Integer[] getIdArray() {
		return idArray;
	}

	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}

	public String[] getStrIdArray() {
		return strIdArray;
	}

	public void setStrIdArray(String[] strIdArray) {
		this.strIdArray = strIdArray;
	}

	public String[] getExt1Array() {
		return ext1Array;
	}

	public void setExt1Array(String[] ext1Array) {
		this.ext1Array = ext1Array;
	}

	public String[] getExt2Array() {
		return ext2Array;
	}

	public void setExt2Array(String[] ext2Array) {
		this.ext2Array = ext2Array;
	}
	
}
