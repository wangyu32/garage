package com.wangyu.prm.service;

import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.exception.RollbackableBizException;
import com.wangyu.prm.model.RoleModel;
import com.wangyu.prm.page.PageQueryResult;
import com.wangyu.prm.parameter.RoleMenuParameter;
import com.wangyu.prm.parameter.RolePageQueryParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;
import com.wangyu.prm.response.ModuleMenuResponse;

import java.util.List;


/**
 * 角色管理接口
 * @author 	wangyu wangyu@joygo.com 2016年10月13日
 *
 */
public interface IRoleService{
	
	/**
	 * 增加角色信息
	 * @param roleModel	角色信息Model
	 * @return 是否增加成功的信息
	 * 
	 */
	String insert(RoleModel roleModel) ;
	
	/**
	 * 增加角色信息及关联菜单
	 * @param model	角色信息Model及关联菜单ID数组
	 * @return 是否增加成功的信息
	 * @throws RollbackableBizException
	 */
	String insertRoleAndRoleMenu(RoleModel model)throws RollbackableBizException;
	
	/**
	 * 修改角色信息
	 * @param model	角色信息Model
	 * @return 是否修改成功的信息
	 * 
	 */
	String update(RoleModel model) ;
	
	/**
	 * 修改角色信息
	 * @param model	角色信息Model及关联菜单ID数组
	 * @return 是否修改成功的信息
	 * @throws RollbackableBizException
	 */
	String updateRoleAndRoleMenu(RoleModel model) throws RollbackableBizException;

	/**
	 * 修改角色状态
	 * @param roleModel	角色信息Model
	 * @return				是否修改成功的信息
	 * 
	 */
	String updateStatus(RoleModel roleModel);
	
	/**
	 * 删除角色
	 * @param roleModel 角色Model
	 * @return 是否删除成功信息
	 */
	String delete(RoleModel roleModel);
	
	/**
	 * 批量删除角色，并且删除对应角色与菜单的关联关系
	 * @param parameter	删除参数
	 * @return 是否删除成功的信息
	 * 
	 */
	String deleteBatch(DeleteParameter parameter) throws RollbackableBizException;
	
	/**
	 * 根据角色id查询角色信息
	 * @param rId	角色id
	 * @return		角色信息Model
	 * 
	 */
	RoleModel findByPrimaryKey(int rId);

	/**
	 * 查询全部角色信息
	 * @return	全部角色信息
	 *
	 */
	List<RoleModel> findAll();

	/**
     * 查询同一项目下角色名称的个数（主要用来校验角色名称，防止名称重复）
     * @param pageQueryParameter - 查询参数
     * @return 记录数
     * 
     */
    int findCountOfRname(RolePageQueryParameter pageQueryParameter);

	/**
	 * 获取用户角色
	 * @param parameter 用户查询参数
	 * @return List 用户角色集合
	 * 
	 */
	List<RoleModel> findRolesByUserId(UserPageQueryParameter parameter);
    
	/**
	 * 根据角色id查询模块菜单列表
	 * @param roleId- 角色id
	 * @return  List - 角色菜单集合
	 */
	List<ModuleMenuResponse> findModuleMenuByRoleId(Integer roleId);

	/**
	 * 查询所有模块菜单列表
	 * @return  List - 角色菜单集合
	 */
	List<ModuleMenuResponse> findAllModuleMenu();
	
	/**
	 * 给角色分配菜单，也做删除角色的菜单（此时传入参数的菜单id为空）
	 * @param roleMenuParameter - 角色分配菜单参数  
	 * @return 是否添加成功的消息 
	 * @throws RollbackableBizException - 可回滚异常
	 */
	String addRoleMenu(RoleMenuParameter roleMenuParameter) throws RollbackableBizException;

    /**
	 * 分页查询
	 * @param parameter - 分页查询参数
	 * @return PageQueryResult
	 */
	PageQueryResult findByPage(RolePageQueryParameter parameter);

	/**
	 * 根据角色ID、项目ID和角色名称查询角色是否已存在，如果存在则返回<br>
	 * 两个使用场景:
	 * 1.添加角色时，角色名称不能与同项目下其他角色名称相同，查询时不需角色ID
	 * 2.修改角色时，角色名称不能与同项目下其他角色名称相同，查询时需要本身的角色ID
	 * @param parameter - 包含角色ID、项目ID和角色名称的参数
	 * @return RoleModel
	 */
//	RoleModel exist(RolePageQueryParameter parameter);

}
