package com.wangyu.prm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wangyu.garage.util.NullUtil;
import com.wangyu.garage.util.StringUtil;
import com.wangyu.prm.common.Code;
import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.constant.CommonConstants;
import com.wangyu.prm.constant.MessageConstants;
import com.wangyu.prm.entity.ModuleMenuQueryResult;
import com.wangyu.prm.exception.RollbackableBizException;
import com.wangyu.prm.mapper.ModuleMenuModelMapper;
import com.wangyu.prm.mapper.UserModelMapper;
import com.wangyu.prm.mapper.UserRoleModelMapper;
import com.wangyu.prm.model.ModuleMenuModel;
import com.wangyu.prm.model.RoleUserCountModel;
import com.wangyu.prm.model.UserModel;
import com.wangyu.prm.model.UserRoleModel;
import com.wangyu.prm.page.PageQueryResult;
import com.wangyu.prm.parameter.ChangePasswordParameter;
import com.wangyu.prm.parameter.UserLoginParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;
import com.wangyu.prm.parameter.UserRoleParameter;
import com.wangyu.prm.response.ModuleMenuResponse;
import com.wangyu.prm.response.UserLoginResponse;
import com.wangyu.prm.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 用户管理服务层实现类
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 上午10:03:54
 *
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	private UserModelMapper userModelMapper;

	@Resource
	private UserRoleModelMapper userRoleModelMapper;
	
	@Resource
	private ModuleMenuModelMapper moduleMenuModelMapper;
	
	@Override
	public String insert(UserModel userModel)  {
		int num = userModelMapper.insert(userModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String insertUserAndUserRole(UserModel userModel) throws RollbackableBizException {
		try {
			//增加用户
			int num = userModelMapper.insert(userModel);
			
			if(NullUtil.notNull(userModel.getR_id_array())){
				Integer u_id = userModel.getU_id();
				String[] r_id_str_array = userModel.getR_id_array();
				List<UserRoleModel> list = new ArrayList<UserRoleModel>(r_id_str_array.length);
				for (int i = 0; i < r_id_str_array.length; i++) {
					UserRoleModel model = new UserRoleModel();
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
	public String update(UserModel userModel)  {
		int num = userModelMapper.updateByPrimaryKey(userModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String updateUserAndUserRole(UserModel userModel) throws RollbackableBizException{
		try {
			//修改用户
			int num = userModelMapper.updateByPrimaryKey(userModel);
			
			Integer u_id = userModel.getU_id();
			
			//删除用户已关联的角色
			userRoleModelMapper.deleteByUserid(u_id);
			
			if(NullUtil.notNull(userModel.getR_id_array())){
				String[] r_id_str_array = userModel.getR_id_array();
				List<UserRoleModel> list = new ArrayList<UserRoleModel>(r_id_str_array.length);
				for (int i = 0; i < r_id_str_array.length; i++) {
					UserRoleModel model = new UserRoleModel();
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
	public String updateStatus(UserModel userModel) {
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
	public UserModel findByPrimaryKey(int uId) {
		return userModelMapper.findByPrimaryKey(uId);
	}

//	@Override
//	public List<UserModel> findAll() {
//		return userModelMapper.findAll();
//	}

//	@Override
//	public UserModel findByLognameAndProjectId(String logname, Integer pid, Integer status) {
//		UserPageQueryParameter parameter = new UserPageQueryParameter();
//		parameter.setU_logname(logname);//用户名
//		parameter.setRef_p_id(pid);//项目id
//		parameter.setU_status(status);//启用的状态
//		return userModelMapper.findByLognameAndProjectId(parameter);
//	}

	@Override
	public int findCountOfUlogname(UserPageQueryParameter parameter) {
		return userModelMapper.findCountOfUlogname(parameter);
	}

	@Override
	public String addUserRole(UserRoleParameter userRoleParameter) throws RollbackableBizException {
		//用户id
		Integer userId = userRoleParameter.getUserid();

		//角色id串
		String roleids = userRoleParameter.getRoleids();
		try {
			//删除角色原有的id
			int num = userRoleModelMapper.deleteByUserid(userId);

			if(StringUtil.notBlank(roleids)){
				//逗号分隔
				String[] array = roleids.split(",");
				
				List<UserRoleModel> list = new ArrayList<UserRoleModel>(array.length);
				for (int i = 0; i < array.length; i++) {
					UserRoleModel model = new UserRoleModel();
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
			List<ModuleMenuModel> itemList = null;
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
					
					itemList = new ArrayList<ModuleMenuModel>();
					resultList.add(moduleMenuResult);
				}
				
				//菜单信息
				ModuleMenuModel item = new ModuleMenuModel();
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
	public PageQueryResult findByPage(UserPageQueryParameter parameter) {
		//根据条件查询总记录数
		int total = userModelMapper.findCountByPage(parameter);
		if(total > 0){
			//根据条件查询具体记录
			List<UserModel> list = userModelMapper.findByPage(parameter);
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
	public String updatePasswordByAdmin(UserModel model){
		UserModel modelOld = userModelMapper.findByPrimaryKeyIncludePassword(model.getU_id());
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
	public List<RoleUserCountModel> findRoleUserCount(DeleteParameter parameter) {
		return userModelMapper.findRoleUserCount(parameter);
	}

	@Override
	public List<UserModel> findAdminUser(DeleteParameter parameter) {
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
