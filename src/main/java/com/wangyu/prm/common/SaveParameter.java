package com.wangyu.prm.common;

/**
 * 通用保存参数（批量保存关系表数据）
 * @author 	wangyu	wangyu@joygo.com 2016年12月8日 10:36:19
 *
 */
public class SaveParameter {
	
	/**
	 * 主表id
	 */
	private Integer main_table_id;
	
	/**
	 * 关联表的主键id的数组
	 */
	private Integer[] idArray;
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;
	
	public Integer[] getIdArray() {
		return idArray;
	}

	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}

	public Integer getMain_table_id() {
		return main_table_id;
	}

	public void setMain_table_id(Integer main_table_id) {
		this.main_table_id = main_table_id;
	}

	public Integer getRef_p_id() {
		return ref_p_id;
	}

	public void setRef_p_id(Integer ref_p_id) {
		this.ref_p_id = ref_p_id;
	}

}
