package com.wangyu.prm.service;

import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.exception.RollbackableBizException;
import com.wangyu.prm.model.RoleUserCountModel;
import com.wangyu.prm.model.UserModel;
import com.wangyu.prm.page.PageQueryResult;
import com.wangyu.prm.parameter.ChangePasswordParameter;
import com.wangyu.prm.parameter.UserLoginParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;
import com.wangyu.prm.parameter.UserRoleParameter;
import com.wangyu.prm.response.ModuleMenuResponse;
import com.wangyu.prm.response.UserLoginResponse;

import java.util.List;

/**
 * 用户管理接口
 * @author 	wangyu	wangyu@joygo.com 2016年10月13日 上午9:36:30
 *
 */
public interface IUserService{

	/**
	 * 增加用户信息
	 * @param userModel	用户信息Model
	 * @return			是否增加成功的信息
	 * 
	 */
	String insert(UserModel userModel) ;

	/**
	 * 增加用户及关联角色
	 * @param userModel	用户信息Model及关联角色ID数组
	 * @return			是否增加成功的信息
	 * @throws RollbackableBizException
	 */
	String insertUserAndUserRole(UserModel userModel) throws RollbackableBizException;
	
	/**
	 * 修改用户信息
	 * @param userModel	用户信息Model
	 * @return				是否修改成功的信息
	 */
	String update(UserModel userModel) ;

	/**
	 * 修改用户信息
	 * @param userModel	用户信息Model及关联角色ID数组
	 * @return			是否修改成功的信息
	 * @throws RollbackableBizException
	 */
	String updateUserAndUserRole(UserModel userModel) throws RollbackableBizException;

	/**
	 * 修改用户状态
	 * @param userModel	用户信息Model
	 * @return				是否修改成功的信息
	 * 
	 */
	String updateStatus(UserModel userModel) ;
	
	/**
	 * 根据用户id删除用户信息
	 * @param uId	用户id
	 * @return		是否删除成功的信息
	 * 
	 */
	String delete(int uId) ;
	
	/**
	 * 批量删除
	 * @param parameter	删除参数
	 * @return 是否删除成功的信息
	 */
	String deleteBatch(DeleteParameter parameter) throws RollbackableBizException;
	
	/**
	 * 根据用户id查询用户信息
	 * @param uId	用户id
	 * @return		用户信息Model
	 * 
	 */
	UserModel findByPrimaryKey(int uId);
	
	/**
	 * 查询全部用户信息
	 * @return	全部用户信息
	 * 
	 */
//	List<UserModel> findAll();
	
	/**
	 * 根据用户登录名和项目id查询用户
	 * @param logname - 登录名
	 * @param pid - 项目id
	 * @param status - 用户状态（0-启用；1-停用）
	 * @return UserModel
	 * 
	 */
//	UserModel findByLognameAndProjectId(String logname, Integer pid, Integer status);

	/**
     * 查询同一项目下登录名的个数（主要用来校验角色名称，防止名称重复）
     * @param parameter - 查询参数
     * @return 记录数
     * 
	 */
	int findCountOfUlogname(UserPageQueryParameter parameter);
	
	/**
	 * 给用户分配角色，也做删除用户的角色（此时传入参数的角色id为空）
	 * @param userRoleParameter - 用户分配角色参数
	 * @return 是否添加成功的消息 
	 * @throws RollbackableBizException - 可回滚异常
	 */
	String addUserRole(UserRoleParameter userRoleParameter) throws RollbackableBizException;
	
	/**
	 * 根据用户id查询模块菜单列表
	 * @param userId- 用户id
	 * @return 模块和菜单集合
	 */
	List<ModuleMenuResponse> findModuleMenuByUserId(Integer userId);
	
	/**
	 * 用户登录查询用户
	 * @param userLoginParameter - 用户登录参数
	 * @return UserModel
	 */
	UserLoginResponse findUserForLogin(UserLoginParameter userLoginParameter);
    
	/**
	 * 分页查询
	 * @param parameter - 分页查询参数
	 * @return PageQueryResult
	 */
	PageQueryResult findByPage(UserPageQueryParameter parameter);

	/**
	 * 修改用户密码(登录用户自己修改)
	 * @param parameter - 修改密码参数 
	 * @return 是否修改成功信息
	 */
	String updatePassword(ChangePasswordParameter parameter);

	/**
	 * 修改用户密码(管理员修改)
	 * @param model - 用户Model
	 * @return 是否修改成功信息
	 */
	String updatePasswordByAdmin(UserModel model);
	
	/**
	 * 修改用户的最后登录时间
	 * @param userid - 用户id
	 * @return 是否修改成功信息
	 */
	String updateLastlogintime(Integer userid);
	
	/**
	 * 根据角色Id数组查询关联的用户个数
	 * @param parameter - 查询参数
	 * @return List
	 */
	List<RoleUserCountModel> findRoleUserCount(DeleteParameter parameter);
	
	/**
	 * 根据项目ID和用户ID数组查询其中的管理员
	 * @param parameter - 项目ID和用户ID数组参数
	 * @return List
	 */
	List<UserModel> findAdminUser(DeleteParameter parameter);

    /**
     * 用户登录
     * @param userLoginParameter - 用户登录参数
     * @return UserLoginResponse
     */
    UserLoginResponse login(UserLoginParameter userLoginParameter);
}
