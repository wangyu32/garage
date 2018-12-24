package com.wangyu.system.common;

/**
 * 通用修改参数（常用批量修改状态）
 * @author 	wangyu	wangyu@joygo.com 2016年12月8日 10:36:19
 *
 */
public class UpdateParameter {
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;
	
	/**
	 * 关联表的主键id的数组
	 */
	private Integer[] idArray;
	
	/**
	 * 状态（批量）
	 */
	private Integer status;

	public Integer[] getIdArray() {
		return idArray;
	}

	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}

	public Integer getRef_p_id() {
		return ref_p_id;
	}

	public void setRef_p_id(Integer ref_p_id) {
		this.ref_p_id = ref_p_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
