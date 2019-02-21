package com.gxc15090111.system.service.impl;

import com.gxc15090111.system.mapper.ProjectModelMapper;
import com.gxc15090111.system.model.ProjectModel;
import com.gxc15090111.system.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目管理服务层的实现类
 * @author 	gxc15090111  2018年10月11日
 *
 */
@Service("projectService")
public class ProjectServiceImpl implements IProjectService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectModelMapper projectModelMapper;

	@Override
	public ProjectModel findByPrimaryKey(int pId) {
		return projectModelMapper.findByPrimaryKey(pId);
	}

}
