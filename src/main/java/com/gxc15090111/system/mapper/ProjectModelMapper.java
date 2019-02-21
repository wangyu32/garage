package com.gxc15090111.system.mapper;


import com.gxc15090111.system.model.ProjectModel;

/**
 * 项目Mapper
 * @author
 *
 */
public interface ProjectModelMapper{
	
    /**
     * 根据主键项目id查询项目
     * @param p_id	项目id
     * @return ProjectModel - 项目Model封装对象
     */
    ProjectModel findByPrimaryKey(Integer p_id);

}