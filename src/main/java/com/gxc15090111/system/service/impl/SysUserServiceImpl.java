package com.gxc15090111.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gxc15090111.garage.util.NullUtil;
import com.gxc15090111.garage.util.StringUtil;
import com.gxc15090111.system.common.Code;
import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.constant.CommonConstants;
import com.gxc15090111.system.constant.MessageConstants;
import com.gxc15090111.system.entity.ModuleMenuQueryResult;
import com.gxc15090111.system.exception.RollbackableBizException;
import com.gxc15090111.system.mapper.SysModuleMenuModelMapper;
import com.gxc15090111.system.mapper.SysUserModelMapper;
import com.gxc15090111.system.mapper.SysUserRoleModelMapper;
import com.gxc15090111.system.model.SysModuleMenuModel;
import com.gxc15090111.system.model.SysRoleUserCountModel;
import com.gxc15090111.system.model.SysUserModel;
import com.gxc15090111.system.model.SysUserRoleModel;
import com.gxc15090111.system.page.PageQueryResult;
import com.gxc15090111.system.parameter.ChangePasswordParameter;
import com.gxc15090111.system.parameter.SysUserPageQueryParameter;
import com.gxc15090111.system.parameter.UserLoginParameter;
import com.gxc15090111.system.parameter.SysUserRoleParameter;
import com.gxc15090111.system.response.ModuleMenuResponse;
import com.gxc15090111.system.response.UserLoginResponse;
import com.gxc15090111.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户管理服务层实现类
 * @author 	gxc15090111	 2018年10月17日 上午10:03:54
 *
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	private SysUserModelMapper userModelMapper;

	@Resource
	private SysUserRoleModelMapper userRoleModelMapper;
	
	@Resource
	private SysModuleMenuModelMapper moduleMenuModelMapper;
	
	@Override
	public String insert(SysUserModel userModel)  {
		int num = userModelMapper.insert(userModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String insertUserAndUserRole(SysUserModel userModel) throws RollbackableBizException {
		try {
			//增加用户
			int num = userModelMapper.insert(userModel);
			
			if(NullUtil.notNull(userModel.getR_id_array())){
				Integer u_id = userModel.getU_id();
				String[] r_id_str_array = userModel.getR_id_array();
				List<SysUserRoleModel> list = new ArrayList<SysUserRoleModel>(r_id_str_array.length);
				for (int i = 0; i < r_id_str_array.length; i++) {
					SysUserRoleModel model = new SysUserRoleModel();
					model.setRef_u_id(u_id);
					model.setRef_r_id(Integer.valueOf(r_id_str_array[i]));
					list.add(model);
				}
				
				//批量添加用户与角色关联关系
				num = userRoleModelMapper.insertList(list);
			}
			
			if(num > 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
		} catch (Exception e) {
			log.error("添加用户及分配角色错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}

	@Override
	public String update(SysUserModel userModel)  {
		int num = userModelMapper.updateByPrimaryKey(userModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String updateUserAndUserRole(SysUserModel userModel) throws RollbackableBizException{
		try {
			//修改用户
			int num = userModelMapper.updateByPrimaryKey(userModel);
			
			Integer u_id = userModel.getU_id();
			
			//删除用户已关联的角色
			userRoleModelMapper.deleteByUserid(u_id);
			
			if(NullUtil.notNull(userModel.getR_id_array())){
				String[] r_id_str_array = userModel.getR_id_array();
				List<SysUserRoleModel> list = new ArrayList<SysUserRoleModel>(r_id_str_array.length);
				for (int i = 0; i < r_id_str_array.length; i++) {
					SysUserRoleModel model = new SysUserRoleModel();
					model.setRef_u_id(u_id);
					model.setRef_r_id(Integer.valueOf(r_id_str_array[i]));
					list.add(model);
				}
				
				//批量添加用户与角色关联关系
				num = userRoleModelMapper.insertList(list);
			}
			if(num > 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
		} catch (Exception e) {
			log.error("修改用户及分配角色错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}
	
	@Override
	public String updateStatus(SysUserModel userModel) {
		int num = userModelMapper.updateStatus(userModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String delete(int userId)  {
		int num = userModelMapper.deleteByPrimaryKey(userId);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String deleteBatch(DeleteParameter parameter) throws RollbackableBizException{
		try {
			//批量删除用户
			int num = userModelMapper.deleteBatch(parameter);
			
			//批量删除用户与角色关联关系
			userRoleModelMapper.deleteBatchByRefUid(parameter);
			
			if(num > 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
			
		} catch (Exception e) {
			log.error("修改用户及分配角色错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}
	
	@Override
	public SysUserModel findByPrimaryKey(int uId) {
		return userModelMapper.findByPrimaryKey(uId);
	}

//	@Override
//	public List<SysUserModel> findAll() {
//		return userModelMapper.findAll();
//	}

//	@Override
//	public SysUserModel findByLognameAndProjectId(String logname, Integer pid, Integer status) {
//		SysUserPageQueryParameter parameter = new SysUserPageQueryParameter();
//		parameter.setU_logname(logname);//用户名
//		parameter.setRef_p_id(pid);//项目id
//		parameter.setU_status(status);//启用的状态
//		return userModelMapper.findByLognameAndProjectId(parameter);
//	}

	@Override
	public int findCountOfUlogname(SysUserPageQueryParameter parameter) {
		return userModelMapper.findCountOfUlogname(parameter);
	}

	@Override
	public String addUserRole(SysUserRoleParameter sysUserRoleParameter) throws RollbackableBizException {
		//用户id
		Integer userId = sysUserRoleParameter.getUserid();

		//角色id串
		String roleids = sysUserRoleParameter.getRoleids();
		try {
			//删除角色原有的id
			int num = userRoleModelMapper.deleteByUserid(userId);

			if(StringUtil.notBlank(roleids)){
				//逗号分隔
				String[] array = roleids.split(",");
				
				List<SysUserRoleModel> list = new ArrayList<SysUserRoleModel>(array.length);
				for (int i = 0; i < array.length; i++) {
					SysUserRoleModel model = new SysUserRoleModel();
					model.setRef_u_id(userId);
					model.setRef_r_id(Integer.valueOf(array[i]));
					list.add(model);
				}
				
				//批量添加
				num = userRoleModelMapper.insertList(list);
			}
			
			if(num > 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
		} catch (Exception e) {
			log.error("给用户分配角色错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}
	
	@Override
	public List<ModuleMenuResponse> findModuleMenuByUserId(Integer userId){
		//根据用户id查询模块菜单列表
		List<ModuleMenuQueryResult> list = moduleMenuModelMapper.findModuleMenuByUserId(userId);
		
		//返回的List
		List<ModuleMenuResponse> resultList = new ArrayList<>();
		
		if(list != null && list.size() > 0){
			Integer moduleId = null;
			ModuleMenuResponse moduleMenuResult = null;
			List<SysModuleMenuModel> itemList = null;
			//临时缓存模块id
			Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
			for (int i = 0; i < list.size(); i++) {
				ModuleMenuQueryResult result = list.get(i);
				//模块id
				moduleId = result.getM_id();
				if(cache.get(moduleId) == null){
					cache.put(moduleId, moduleId);
					//模块信息
					moduleMenuResult = new ModuleMenuResponse();
					moduleMenuResult.setM_id(result.getM_id());
					moduleMenuResult.setM_image(result.getM_image());
					moduleMenuResult.setM_name(result.getM_name());
					moduleMenuResult.setM_order(result.getMm_order());
					moduleMenuResult.setM_type(result.getM_type());
					
					itemList = new ArrayList<SysModuleMenuModel>();
					resultList.add(moduleMenuResult);
				}
				
				//菜单信息
				SysModuleMenuModel item = new SysModuleMenuModel();
				item.setMm_id(result.getMm_id());
				item.setMm_name(result.getMm_name());
				item.setMm_order(result.getMm_order());
				item.setMm_url(result.getMm_url());
				itemList.add(item);
				
				moduleMenuResult.setItems(itemList);
			}
		}
		return resultList;
	}

	@Override
	public UserLoginResponse findUserForLogin(UserLoginParameter userLoginParameter) {
		return userModelMapper.findUserForLogin(userLoginParameter);
	}

	@Override
	public PageQueryResult findByPage(SysUserPageQueryParameter parameter) {
		//根据条件查询总记录数
		int total = userModelMapper.findCountByPage(parameter);
		if(total > 0){
			//根据条件查询具体记录
			List<SysUserModel> list = userModelMapper.findByPage(parameter);
			return new PageQueryResult(list, total);
		}
		return new PageQueryResult();
	}

	@Override
	public String updatePassword(ChangePasswordParameter parameter) {
		int num = userModelMapper.updatePassword(parameter);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String updatePasswordByAdmin(SysUserModel model){
		SysUserModel modelOld = userModelMapper.findByPrimaryKeyIncludePassword(model.getU_id());
		String oldPassword = modelOld.getU_password();
		
		int num = userModelMapper.updatePasswordByAdmin(model);
		if(num > 0){
			model.setU_password(oldPassword);//修改成功，将旧密码返回
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String updateLastlogintime(Integer userid) {
		int num = userModelMapper.updateLastlogintime(userid);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public List<SysRoleUserCountModel> findRoleUserCount(DeleteParameter parameter) {
		return userModelMapper.findRoleUserCount(parameter);
	}

	@Override
	public List<SysUserModel> findAdminUser(DeleteParameter parameter) {
		return userModelMapper.findAdminUser(parameter);
	}

	@Override
	public UserLoginResponse login(UserLoginParameter userLoginParameter) {
        //1.验证用户在该项目下是否存在
        UserLoginResponse userLoginResponse = this.findUserForLogin(userLoginParameter);

        //用户不存在，或者状态不为0
        if(userLoginResponse == null || userLoginResponse.getU_status().intValue() != CommonConstants.COMMON_STATUS_NORMAL){
            return new UserLoginResponse().setCode(Code.FAIL).setMessage(MessageConstants.USER_PASSWORD_ERROR);
        }

        //数据库中用户密码
        String passwordDB = userLoginResponse.getU_password();

        //2.验证用户名密码是否正确
        if(userLoginParameter.getPassword().equals(passwordDB)){
            //获取用户菜单
            List<ModuleMenuResponse> list = this.findModuleMenuByUserId(userLoginResponse.getU_id());
            userLoginResponse.setMenus(list);

            //修改最后登录时间
            this.updateLastlogintime(userLoginResponse.getU_id());

            return userLoginResponse;
        } else {
            userLoginResponse.setCode(Code.FAIL);
            userLoginResponse.setMessage(MessageConstants.USER_PASSWORD_ERROR);
            return userLoginResponse;
        }
	}
}
