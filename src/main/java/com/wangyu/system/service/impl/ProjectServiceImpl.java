package com.wangyu.system.service.impl;

import com.wangyu.system.mapper.ProjectModelMapper;
import com.wangyu.system.model.ProjectModel;
import com.wangyu.system.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目管理服务层的实现类
 * @author 	wangyu wangyu@joygo.com 2016年10月11日
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
