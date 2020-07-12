package com.wangyu.entity.parameter;

import com.wangyu.entity.page.PageQueryParameter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色信息查询参数
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class RolePageQueryParameter extends PageQueryParameter {
	
	/**
	 * 角色id
	 */
	private Integer id;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
	private Integer status;

}
