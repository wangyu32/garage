package com.wangyu.service.impl;

import com.wangyu.model.UserLog;
import com.wangyu.mapper.UserLogMapper;
import com.wangyu.service.IUserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements IUserLogService {

}
