package com.wangyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyu.common.Code;
import com.wangyu.entity.RoleUserCountModel;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.DeleteParameter;
import com.wangyu.entity.parameter.RolePageQueryParameter;
import com.wangyu.entity.parameter.UserPageQueryParameter;
import com.wangyu.entity.parameter.UserRolePageQueryParameter;
import com.wangyu.entity.vo.UserRoleCheckedVO;
import com.wangyu.mapper.SysRoleMenuMapper;
import com.wangyu.mapper.SysUserMapper;
import com.wangyu.mapper.SysUserRoleMapper;
import com.wangyu.model.SysRole;
import com.wangyu.mapper.SysRoleMapper;
import com.wangyu.model.SysRoleMenu;
import com.wangyu.model.SysUserRole;
import com.wangyu.response.UserRoleCheckedVOListResponse;
import com.wangyu.service.ISysRoleMenuService;
import com.wangyu.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyu.util.NullUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public PageQueryResult findByPage(RolePageQueryParameter parameter) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (parameter.getId() != null) {
            wrapper.lambda().eq(SysRole::getId, parameter.getId());
        }
        if (StringUtils.isNotBlank(parameter.getName())) {
            wrapper.lambda().like(SysRole::getName, parameter.getName());
        }
        if (parameter.getStatus() != null) {
            wrapper.lambda().eq(SysRole::getStatus, parameter.getStatus());
        }
        if (StringUtils.isNotBlank(parameter.getSort())) {
            if(parameter.isAsc()){
                wrapper.orderByAsc(parameter.getSort());
            } else {
                wrapper.orderByDesc(parameter.getSort());
            }
        }

        if (parameter.isPageQuery()) {
            Page<SysRole> page = new Page<>(parameter.getPageNumber(), parameter.getLimit());
            IPage<SysRole> iPage = this.sysRoleMapper.selectPage(page, wrapper);
            PageQueryResult pageQueryResult = new PageQueryResult(iPage.getRecords(), Long.valueOf(iPage.getTotal()).intValue());
            return pageQueryResult;
        } else {
            List<SysRole> list = this.sysRoleMapper.selectList(wrapper);
            return new PageQueryResult(list, list.size());
        }
    }

    @Override
    public int findCountOfRname(RolePageQueryParameter pageQueryParameter) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysRole::getName, pageQueryParameter.getName());
        if(pageQueryParameter.getId() != null){
            wrapper.lambda().ne(SysRole::getId, pageQueryParameter.getId());
        }
        return this.sysRoleMapper.selectCount(wrapper);
    }

    @Transactional
    @Override
    public String insertRoleAndRoleMenu(SysRole roleModel) {
        try {
            //增加角色
            int num = sysRoleMapper.insert(roleModel);

            if(NullUtil.notNull(roleModel.getMmIdArray())){
                Integer r_id = roleModel.getId();
                String[] mmIdArray = roleModel.getMmIdArray();
                List<SysRoleMenu> list = new ArrayList<>(mmIdArray.length);
                for (int i = 0; i < mmIdArray.length; i++) {
                    SysRoleMenu model = new SysRoleMenu();
                    model.setRid(r_id);
                    model.setMmid(Integer.valueOf(mmIdArray[i]));
                    list.add(model);
                }

                //批量添加角色与菜单关联关系
                num = sysRoleMenuService.saveBatch(list) == true ? list.size() : 0;
            }

            if(num > 0){
                return Code.SUCCESS;
            }else{
                return Code.FAIL;
            }
        } catch (Exception e) {
            log.error("添加用户及分配角色错误:" + e.getMessage());
            return Code.FAIL;
        }
    }

    @Override
    public String updateRoleAndRoleMenu(SysRole roleModel) {
        try {
            //修改角色
            int num = sysRoleMapper.updateById(roleModel);

            Integer r_id = roleModel.getId();

            //删除角色已关联的菜单
            UpdateWrapper<SysRoleMenu> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().eq(SysRoleMenu::getRid, r_id);
            sysRoleMenuMapper.delete(updateWrapper);

            if(NullUtil.notNull(roleModel.getMmIdArray())){
                String[] mmIdArray = roleModel.getMmIdArray();
                List<SysRoleMenu> list = new ArrayList<>(mmIdArray.length);
                for (int i = 0; i < mmIdArray.length; i++) {
                    SysRoleMenu model = new SysRoleMenu();
                    model.setRid(r_id);
                    model.setMmid(Integer.valueOf(mmIdArray[i]));
                    list.add(model);
                }

                //批量添加角色与菜单关联关系
                num = sysRoleMenuService.saveBatch(list) == true ? list.size() : 0;
            }

            if(num > 0){
                return Code.SUCCESS;
            }else{
                return Code.FAIL;
            }
        } catch (Exception e) {
            log.error("添加用户及分配角色错误:" + e.getMessage());
            return Code.FAIL;
        }
    }

    @Override
    public List<RoleUserCountModel> findRoleUserCount(DeleteParameter parameter) {
        return sysUserMapper.findRoleUserCount(parameter);
    }

    @Override
    public UserRoleCheckedVOListResponse queryUserRoleChecked(UserRolePageQueryParameter parameter) {
        UserRoleCheckedVOListResponse listResponse = new UserRoleCheckedVOListResponse();

        RolePageQueryParameter rolePageQueryParameter = new RolePageQueryParameter();
        rolePageQueryParameter.setStatus(parameter.getRstatus());
        rolePageQueryParameter.setPageQuery(parameter.isPageQuery());
        rolePageQueryParameter.setOrder(parameter.getOrder());
        rolePageQueryParameter.setSort(parameter.getSort());
        rolePageQueryParameter.setOffset(parameter.getOffset());
        rolePageQueryParameter.setLimit(parameter.getLimit());

        PageQueryResult result = this.findByPage(rolePageQueryParameter);
        //全部角色
        List<SysRole> allRoleList = result.getResultList();

        //关联用户的缓存Map
        Map<Integer, SysUserRole> userCache = new HashMap<>();

        //用户ID不为空时，是修改用户操作，需要查询出关联的角色
        if(parameter.getUid() != null){

            QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
            sysUserRoleQueryWrapper.lambda().eq(SysUserRole::getUid, parameter.getUid());
            //用户关联的角色
            List<SysUserRole> userRoleList = this.sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);

            if(NullUtil.notNull(userRoleList)){
                for (int i = 0; i < userRoleList.size(); i++) {
                    SysUserRole model = userRoleList.get(i);
                    userCache.put(model.getRid(), model);
                }
            }
        }

        List<UserRoleCheckedVO> voList = new ArrayList<UserRoleCheckedVO>();
        for (int i = 0; i < allRoleList.size(); i++) {
            SysRole model = allRoleList.get(i);
            UserRoleCheckedVO vo = new UserRoleCheckedVO();
            vo.setId(model.getId());
            vo.setName(model.getName());
            vo.setStatus(model.getStatus());
            vo.setDesc(model.getDesc());
            if(userCache.get(model.getId()) != null){
                vo.setChecked(true);//关联的角色
            }
            voList.add(vo);
        }

        listResponse.setList(voList);
        listResponse.setTotal(voList.size());
        return listResponse;

    }
}
