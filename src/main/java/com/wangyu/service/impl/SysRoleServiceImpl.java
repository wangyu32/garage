package com.wangyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.RolePageQueryParameter;
import com.wangyu.model.SysRole;
import com.wangyu.mapper.SysRoleMapper;
import com.wangyu.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageQueryResult findByPage(RolePageQueryParameter parameter) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if(parameter.getId() != null){
            wrapper.lambda().eq(SysRole::getId, parameter.getId());
        }
        if(StringUtils.isNotBlank(parameter.getName())){
            wrapper.lambda().like(SysRole::getName, parameter.getName());
        }
        if(parameter.getStatus() != null){
            wrapper.lambda().eq(SysRole::getStatus, parameter.getStatus());
        }

        if(parameter.isPageQuery()){
            Page<SysRole> page = new Page<>(parameter.getPageNumber(), parameter.getLimit());
            IPage<SysRole> iPage = this.sysRoleMapper.selectPage(page, wrapper);
            PageQueryResult pageQueryResult = new PageQueryResult(iPage.getRecords(), Long.valueOf(iPage.getTotal()).intValue());
            return pageQueryResult;
        } else {
            List<SysRole> list = this.sysRoleMapper.selectList(wrapper);
            return new PageQueryResult(list, list.size());
        }
    }

}
