package com.gxc15090111.system.service.impl;
import java.util.List;

import javax.annotation.Resource;

import com.gxc15090111.system.common.Code;
import com.gxc15090111.system.mapper.UserLogModelMapper;
import com.gxc15090111.system.model.UserLogModel;
import com.gxc15090111.system.page.PageQueryResult;
import com.gxc15090111.system.parameter.UserLogPageQueryParameter;
import com.gxc15090111.system.service.IUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



/**
 * 用户操作日志服务层实现类
 * @author 	wangyu@joygo.com 2017年3月9日
 *
 */
@Slf4j
@Service("userLogService")
public class UserLogServiceImpl implements IUserLogService {

	private static final Logger logger = LoggerFactory.getLogger(UserLogServiceImpl.class);

	@Resource
	private UserLogModelMapper userLogModelMapper;

	@Override
	public String save(UserLogModel userLogModel)  {
		int num = userLogModelMapper.insert(userLogModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public PageQueryResult findByPage(UserLogPageQueryParameter parameter) {
		//根据条件查询总记录数
		int total = userLogModelMapper.findCountByPage(parameter);
		if(total > 0){
			//根据条件查询具体记录
			List<UserLogModel> list = userLogModelMapper.findByPage(parameter);
			return new PageQueryResult(list, total);
		}
		return new PageQueryResult();
	}

}
