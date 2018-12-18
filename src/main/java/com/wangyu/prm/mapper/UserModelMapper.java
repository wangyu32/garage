package com.wangyu.prm.mapper;

import java.util.List;

import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.model.RoleUserCountModel;
import com.wangyu.prm.model.UserModel;
import com.wangyu.prm.page.PageQueryParameter;
import com.wangyu.prm.parameter.ChangePasswordParameter;
import com.wangyu.prm.parameter.UserLoginParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;
import com.wangyu.prm.response.UserLoginResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息Mapper
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 上午9:38:03
 *
 */
@Mapper
public interface UserModelMapper {
	
	/**
	 * 根据用户id删除用户信息
	 * @param u_id	用户id
	 * @return int	删除的记录数
	 */
    int deleteByPrimaryKey(Integer u_id);
	
    /**
	 * 批量删除
	 * @param parameter 删除参数
	 * @return 删除的记录数
	 */
	int deleteBatch(DeleteParameter parameter);
	
    /**
     * 增加1个用户记录
     * @param record	用户信息Model封装对象
     * @return int		增加的记录数
     */
    int insert(UserModel record);

    /**
     * 修改用户信息
     * @param record	用户信息Model封装对象
     * @return			修改的记录数
     */
    int updateByPrimaryKey(UserModel record);

    /**
     * 修改用户状态
     * @param userModel	用户信息Model封装对象
     * @return			修改的记录数
     */
    int updateStatus(UserModel userModel);
    
    /**
     * 根据主键用户id查询用户信息(不包含密码)
     * @param u_id - 用户id
     * @return ProjectModel - 用户信息Model封装对象
     */
    UserModel findByPrimaryKey(Integer u_id);

    /**
     * 根据主键用户id查询用户信息（包括密码）
     * @param u_id - 用户id
     * @return ProjectModel - 用户信息Model封装对象
     */
    UserModel findByPrimaryKeyIncludePassword(Integer u_id);

    /**
     * 查询全部用户
     * @return List	全部用户
     */
//    List<UserModel> findAll();

    /**
	 * 根据用户登录名和项目id查询用户
	 * @param pageQueryParameter - 用户信息查询参数
	 * @return UserModel
	 */
//    UserModel findByLognameAndProjectId(UserPageQueryParameter pageQueryParameter);

    /**
     * 查询同一项目下登录名的个数（主要用来校验登录名，防止名称重复）
     * @param parameter  - 用户信息查询参数
     * @return 记录数
     */
	int findCountOfUlogname(PageQueryParameter parameter);
	
	/**
	 * 用户登录查询用户
	 * @param userLoginParameter - 用户登录参数
	 * @return UserModel
	 */
	UserLoginResponse findUserForLogin(UserLoginParameter userLoginParameter);
	
	   /**
     * 分页查询总记录数
     * @param parameter - 分页查询参数
     * @return 总记录数
     */
    int findCountByPage(UserPageQueryParameter parameter);
    
    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return List
     */
    List<UserModel> findByPage(UserPageQueryParameter parameter);

    /**
	 * 修改用户密码(登录用户自己修改)
	 * @param parameter - 修改密码参数 
	 * @return 修改的记录数
	 */
	int updatePassword(ChangePasswordParameter parameter);

	/**
	 * 修改用户密码(管理员修改)
	 * @param model - 用户Model
	 * @return 修改的记录数
	 */
	int updatePasswordByAdmin(UserModel model);
	
	/**
	 * 修改用户的最后登录时间
	 * @param userid - 用户id
	 * @return 修改的记录数
	 */
	int updateLastlogintime(Integer userid);
 
	
	/**
	 *
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
}