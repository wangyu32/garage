package com.wangyu.entity.vo;

import lombok.Data;

/**
 * 用户关联角色的选择Vo
 * @author 	wangyu
 *
 */
@Data
public class UserRoleCheckedVO {

	/**
	 * 用户是否关联的角色：true-关联；false-未关联
	 */
	private boolean checked;
	
	/**
	 * 角色id
	 */
    private Integer id;
	
	/**
	 * 角色名称
	 */
    private String name;
	
	/**
	 * 名称描述
	 */
    private String desc;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
    private Integer status;

}
