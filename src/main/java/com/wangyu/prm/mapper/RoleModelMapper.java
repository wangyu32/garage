package com.wangyu.prm.mapper;

import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.model.RoleModel;
import com.wangyu.prm.parameter.RolePageQueryParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;

import java.util.List;

/**
 * 角色信息Mapper
 * @author 	wangyu
 * 	2016年10月16日
 *
 */
public interface RoleModelMapper {
    
    /**
     * 删除角色
     * @param roleModel - 角色model
     * @return 删除的记录数
     */
    int delete(RoleModel roleModel);

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
    int insert(RoleModel record);

    /**
     * 修改角色信息
     * @param record	角色信息Model封装对象
     * @return			修改的记录数
     */
    int updateByPrimaryKey(RoleModel record);
	
    /**
	 * 修改角色状态
	 * @param roleModel	角色信息Model封装对象
	 * @return				是否修改成功的信息
	 * 
	 */
    int updateStatus(RoleModel roleModel);
	
    /**
     * 根据主键角色id查询角色信息
     * @param r_id	角色id
     * @return		角色信息Model封装对象
     */
    RoleModel findByPrimaryKey(Integer r_id);

    /**
     * 查询全部角色
     * @return	全部角色
     */
    List<RoleModel> findAll();

	/**
     * 查询同一项目下角色名称的个数（主要用来校验角色名称，防止名称重复）
     * @param pageQueryParameter - 查询参数
     * @return 记录数
     */
    int findCountOfRname(RolePageQueryParameter pageQueryParameter);

    /**
     * 根据用户id查询对应角色
     * @param parameter 用户id
     * @return List
     */
    public List<RoleModel> findRolesByUserId(UserPageQueryParameter parameter);
    
    /**
     * 分页查询总记录数
     * @param parameter - 分页查询参数
     * @return 总记录数
     */
    int findCountByPage(RolePageQueryParameter parameter);
    
    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return List
     */
    List<RoleModel> findByPage(RolePageQueryParameter parameter);

//	/**
//	 * 根据角色ID、项目ID和角色名称查询角色是否已存在，如果存在则返回<br>
//	 * 两个使用场景:
//	 * 1.添加角色时，角色名称不能与同项目下其他角色名称相同，查询时不需角色ID
//	 * 2.修改角色时，角色名称不能与同项目下其他角色名称相同，查询时需要本身的角色ID
//	 * @param parameter - 包含角色ID、项目ID和角色名称的参数
//	 * @return RoleModel
//	 */
//	RoleModel exist(RolePageQueryParameter parameter);

 
}