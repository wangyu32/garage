package com.wangyu.entity;

import lombok.Data;

/**
 * 角色关联用户数量Model
 * @author 	wangyu
 *
 */
@Data
public class RoleUserCountModel {
	
	/**
	 * 角色id
	 */
    private Integer rId;

    /**
     * 角色名称
     */
    private String rName;
	
	/**
	 * 角色关联用户数量
	 */
    private Integer userCount;

}