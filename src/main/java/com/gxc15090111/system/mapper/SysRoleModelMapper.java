package com.gxc15090111.system.mapper;

import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.model.SysRoleModel;
import com.gxc15090111.system.parameter.SysRolePageQueryParameter;
import com.gxc15090111.system.parameter.SysUserPageQueryParameter;

import java.util.List;

/**
 * 角色信息Mapper
 * @author 	gxc15090111
 * 	2018年10月16日
 *
 */
public interface SysRoleModelMapper {
    
    /**
     * 删除角色
     * @param roleModel - 角色model
     * @return 删除的记录数
     */
    int delete(SysRoleModel roleModel);

	/**
	 * 批量删除
	 * @param parameter 删除参数
	 * @return 删除的记录数
	 */
	int deleteBatch(DeleteParameter parameter);
    
    /**
     * 增加1个角色记录
     * @param record	角色信息Model封装对象
     * @return			增加的记录数
     */
    int insert(SysRoleModel record);

    /**
     * 修改角色信息
     * @param record	角色信息Model封装对象
     * @return			修改的记录数
     */
    int updateByPrimaryKey(SysRoleModel record);
	
    /**
	 * 修改角色状态
	 * @param roleModel	角色信息Model封装对象
	 * @return				是否修改成功的信息
	 * 
	 */
    int updateStatus(SysRoleModel roleModel);
	
    /**
     * 根据主键角色id查询角色信息
     * @param r_id	角色id
     * @return		角色信息Model封装对象
     */
    SysRoleModel findByPrimaryKey(Integer r_id);

    /**
     * 查询全部角色
     * @return	全部角色
     */
    List<SysRoleModel> findAll();

	/**
     * 查询同一项目下角色名称的个数（主要用来校验角色名称，防止名称重复）
     * @param pageQueryParameter - 查询参数
     * @return 记录数
     */
    int findCountOfRname(SysRolePageQueryParameter pageQueryParameter);

    /**
     * 根据用户id查询对应角色
     * @param parameter 用户id
     * @return List
     */
    public List<SysRoleModel> findRolesByUserId(SysUserPageQueryParameter parameter);
    
    /**
     * 分页查询总记录数
     * @param parameter - 分页查询参数
     * @return 总记录数
     */
    int findCountByPage(SysRolePageQueryParameter parameter);
    
    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return List
     */
    List<SysRoleModel> findByPage(SysRolePageQueryParameter parameter);

//	/**
//	 * 根据角色ID、项目ID和角色名称查询角色是否已存在，如果存在则返回<br>
//	 * 两个使用场景:
//	 * 1.添加角色时，角色名称不能与同项目下其他角色名称相同，查询时不需角色ID
//	 * 2.修改角色时，角色名称不能与同项目下其他角色名称相同，查询时需要本身的角色ID
//	 * @param parameter - 包含角色ID、项目ID和角色名称的参数
//	 * @return SysRoleModel
//	 */
//	SysRoleModel exist(SysRolePageQueryParameter parameter);

 
}