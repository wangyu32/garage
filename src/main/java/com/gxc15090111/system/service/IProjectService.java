package com.gxc15090111.system.service;

import com.gxc15090111.system.model.ProjectModel;

/**
 * 项目管理服务层接口
 * @author
 *
 */
public interface IProjectService{

	/**
	 * 根据项目id查询项目
	 * @param pId	项目id
	 * @return		项目Model
	 * 
	 */
	ProjectModel findByPrimaryKey(int pId);
}
