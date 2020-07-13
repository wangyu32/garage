package com.wangyu.entity.parameter;

import com.wangyu.entity.page.PageQueryParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联关系查询参数，可分页
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class UserRolePageQueryParameter extends PageQueryParameter {
	
	/**
	 * 用户id
	 */
	private Integer uid;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
	private Integer rstatus;

}
