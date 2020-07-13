package com.wangyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyu.common.Code;
import com.wangyu.constant.CommonConstants;
import com.wangyu.constant.MessageConstants;
import com.wangyu.entity.ModuleMenuQueryResult;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.UserLoginParameter;
import com.wangyu.entity.parameter.UserPageQueryParameter;
import com.wangyu.mapper.SysModuleMenuMapper;
import com.wangyu.model.SysModuleMenu;
import com.wangyu.model.SysUser;
import com.wangyu.mapper.SysUserMapper;
import com.wangyu.response.ModuleMenuResponse;
import com.wangyu.response.UserLoginResponse;
import com.wangyu.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyu.util.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作员表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysModuleMenuMapper sysModuleMenuMapper;

    @Override
    public SysUser findUserForLogin(UserLoginParameter userLoginParameter) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(SysUser::getDel,"0")
                .eq(SysUser::getLogname, userLoginParameter.getLogname());

        List<SysUser> sysUserList = sysUserMapper.selectList(wrapper);
        if(NullUtil.notNull(sysUserList)){
            SysUser sysUser = sysUserList.get(0);
            return sysUser;
        }

        return null;
    }

    @Override
    public String updateLastlogintime(Integer userid) {
        int num = sysUserMapper.updateLastlogintime(userid);
        if (num > 0) {
            return Code.SUCCESS;
        } else {
            return Code.FAIL;
        }
    }

    @Override
    public List<ModuleMenuResponse> findModuleMenuByUserId(Integer userId) {
        //根据用户id查询模块菜单列表
        List<ModuleMenuQueryResult> list = sysModuleMenuMapper.findModuleMenuByUserId(userId);

        //返回的List
        List<ModuleMenuResponse> resultList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            Integer moduleId = null;
            ModuleMenuResponse moduleMenuResult = null;
            List<SysModuleMenu> itemList = null;
            //临时缓存模块id
            Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
            for (int i = 0; i < list.size(); i++) {
                ModuleMenuQueryResult result = list.get(i);
                //模块id
                moduleId = result.getId();
                if (cache.get(moduleId) == null) {
                    cache.put(moduleId, moduleId);
                    //模块信息
                    moduleMenuResult = new ModuleMenuResponse();
                    moduleMenuResult.setMid(result.getId());
                    moduleMenuResult.setMimage(result.getImage());
                    moduleMenuResult.setMname(result.getName());
                    moduleMenuResult.setMorder(result.getOrder());
                    moduleMenuResult.setMtype(result.getType());

                    itemList = new ArrayList<>();
                    resultList.add(moduleMenuResult);
                }

                //菜单信息
                SysModuleMenu item = new SysModuleMenu();
                item.setId(result.getMmid());
                item.setName(result.getMmname());
                item.setOrder(result.getMmorder());
                item.setUrl(result.getMmurl());
                itemList.add(item);

                moduleMenuResult.setItems(itemList);
            }
        }
        return resultList;
    }

    @Override
    public UserLoginResponse login(UserLoginParameter userLoginParameter) {
        //1.验证用户在该项目下是否存在
        SysUser sysUser = this.findUserForLogin(userLoginParameter);

        //用户不存在，或者状态不为0
        if (sysUser == null || sysUser.getStatus().intValue() != CommonConstants.COMMON_STATUS_NORMAL) {
            return new UserLoginResponse().setCode(Code.FAIL).setMessage(MessageConstants.USER_PASSWORD_ERROR);
        }

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUId(sysUser.getId());
        userLoginResponse.setULogname(sysUser.getLogname());
        userLoginResponse.setURealname(sysUser.getRealname());

        //数据库中用户密码
        String passwordDB = sysUser.getPassword();

        //2.验证用户名密码是否正确
        if (userLoginParameter.getPassword().equals(passwordDB)) {
            //获取用户菜单
            List<ModuleMenuResponse> list = this.findModuleMenuByUserId(sysUser.getId());
            userLoginResponse.setMenus(list);

            //修改最后登录时间
            this.updateLastlogintime(userLoginResponse.getUId());

            return userLoginResponse;
        } else {
            userLoginResponse.setCode(Code.FAIL);
            userLoginResponse.setMessage(MessageConstants.USER_PASSWORD_ERROR);
            return userLoginResponse;
        }
    }

    @Override
    public PageQueryResult findByPage(UserPageQueryParameter parameter) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
//        wrapper.lambda()
//                .in(SysUser::getId, parameter.getRef_u_id_array())
//                .eq(SysUser::getId, parameter.get_id())
//                .ne(SysUser::getId, parameter.get_id_not_equals())
//                .ne(SysUser::getIsadmin, 1)
//                .like(SysUser::getLogname, parameter.get_logname())
//                .like(SysUser::getRealname, parameter.get_realname())
//                .like(SysUser::getEmail, parameter.get_email())
//                .like(SysUser::getMobilephone, parameter.get_mobilephone())
//                .eq(SysUser::getStatus, parameter.get_status());
//        ;

        if(parameter.getIdArray() != null){
            wrapper.lambda().in(SysUser::getId, parameter.getIdArray());
        }
        if(parameter.getId() != null){
            wrapper.lambda().eq(SysUser::getId, parameter.getId());
        }
        if(parameter.getIdNotEquals() != null){
            wrapper.lambda().ne(SysUser::getId, parameter.getIdNotEquals());
        }
        if(parameter.getIsAdminNotEquals() != null){
            wrapper.lambda().ne(SysUser::getIsadmin, parameter.getIsAdminNotEquals());
        }
        if(StringUtils.isNotBlank(parameter.getLogname())){
            wrapper.lambda().like(SysUser::getLogname, parameter.getLogname());
        }
        if(StringUtils.isNotBlank(parameter.getRealname())){
            wrapper.lambda().like(SysUser::getRealname, parameter.getRealname());
        }
        if(StringUtils.isNotBlank(parameter.getEmail())){
            wrapper.lambda().like(SysUser::getEmail, parameter.getEmail());
        }
        if(StringUtils.isNotBlank(parameter.getMobilephone())){
            wrapper.lambda().like(SysUser::getMobilephone, parameter.getMobilephone());
        }
        if(parameter.getStatus() != null){
            wrapper.lambda().eq(SysUser::getStatus, parameter.getStatus());
        }
        if (StringUtils.isNotBlank(parameter.getSort())) {
            if(parameter.isAsc()){
                wrapper.orderByAsc(parameter.getSort());
            } else {
                wrapper.orderByDesc(parameter.getSort());
            }
        }
        Page<SysUser> page = new Page<>(parameter.getPageNumber(), parameter.getLimit());
        IPage<SysUser> iPage = this.sysUserMapper.selectPage(page, wrapper);
        PageQueryResult pageQueryResult = new PageQueryResult(iPage.getRecords(), Long.valueOf(iPage.getTotal()).intValue());
        return pageQueryResult;
    }
}
