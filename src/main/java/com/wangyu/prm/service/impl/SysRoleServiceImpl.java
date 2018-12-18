package com.wangyu.prm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wangyu.garage.util.NullUtil;
import com.wangyu.prm.common.Code;
import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.mapper.ModuleMenuModelMapper;
import com.wangyu.prm.mapper.RoleMenuModelMapper;
import com.wangyu.prm.mapper.RoleModelMapper;
import com.wangyu.prm.page.PageQueryResult;
import com.wangyu.prm.parameter.RoleMenuParameter;
import com.wangyu.prm.parameter.RolePageQueryParameter;
import com.wangyu.prm.parameter.UserPageQueryParameter;
import com.wangyu.prm.parameter.UserRolePageQueryParameter;
import com.wangyu.prm.response.ModuleMenuResponse;
import com.wangyu.prm.response.UserRoleCheckedVOListResponse;
import com.wangyu.prm.service.ISysRoleService;
import com.wangyu.prm.vo.UserRoleCheckedVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wangyu.prm.entity.ModuleMenuQueryResult;
import com.wangyu.prm.exception.RollbackableBizException;
import com.wangyu.prm.model.ModuleMenuModel;
import com.wangyu.prm.model.RoleMenuModel;
import com.wangyu.prm.model.RoleModel;

/**
 * 角色管理服务实现类
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 上午10:03:43
 *
 */
@Slf4j
@Service("roleService")
public class SysRoleServiceImpl implements ISysRoleService {
	
	@Resource
	private ModuleMenuModelMapper moduleMenuModelMapper;
	
	@Resource
	private RoleModelMapper roleModelMapper;
	
	@Resource
	private RoleMenuModelMapper roleMenuModelMapper;

	@Override
	public String insert(RoleModel roleModel)  {
		int num = roleModelMapper.insert(roleModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}

	@Override
	public String insertRoleAndRoleMenu(RoleModel roleModel) throws RollbackableBizException{
		try {
			//增加角色
			int num = roleModelMapper.insert(roleModel);
			
			if(NullUtil.notNull(roleModel.getMm_id_array())){
				Integer r_id = roleModel.getR_id();
				String[] mm_id_str_array = roleModel.getMm_id_array();
				List<RoleMenuModel> list = new ArrayList<RoleMenuModel>(mm_id_str_array.length);
				for (int i = 0; i < mm_id_str_array.length; i++) {
					RoleMenuModel model = new RoleMenuModel();
					model.setRef_r_id(r_id);
					model.setRef_mm_id(Integer.valueOf(mm_id_str_array[i]));
					list.add(model);
				}
				
				//批量添加角色与菜单关联关系
				num = roleMenuModelMapper.insertList(list);
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
	public String update(RoleModel roleModel)  {
		int num = roleModelMapper.updateByPrimaryKey(roleModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String updateRoleAndRoleMenu(RoleModel roleModel) throws RollbackableBizException{
		try {
			//修改角色
			int num = roleModelMapper.updateByPrimaryKey(roleModel);
			
			Integer r_id = roleModel.getR_id();
			
			//删除角色已关联的菜单
			roleMenuModelMapper.deleteByRoleid(r_id);
			
			if(NullUtil.notNull(roleModel.getMm_id_array())){
				String[] mm_id_str_array = roleModel.getMm_id_array();
				List<RoleMenuModel> list = new ArrayList<RoleMenuModel>(mm_id_str_array.length);
				for (int i = 0; i < mm_id_str_array.length; i++) {
					RoleMenuModel model = new RoleMenuModel();
					model.setRef_r_id(r_id);
					model.setRef_mm_id(Integer.valueOf(mm_id_str_array[i]));
					list.add(model);
				}
				
				//批量添加角色与菜单关联关系
				num = roleMenuModelMapper.insertList(list);
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
	public String updateStatus(RoleModel roleModel) {
		int num = roleModelMapper.updateStatus(roleModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String delete(RoleModel roleModel) {
		int num = roleModelMapper.delete(roleModel);
		if(num > 0){
			return Code.SUCCESS;
		}else{
			return Code.FAIL;
		}
	}
	
	@Override
	public String deleteBatch(DeleteParameter parameter) throws RollbackableBizException{
		try {
			//1.先删除角色（逻辑删除）
			int num = roleModelMapper.deleteBatch(parameter);
			
			//2.删除角色与菜单关系（物理删除）
			roleMenuModelMapper.deleteByRoleidArray(parameter);

			if(num > 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
		} catch (Exception e) {
			log.error("删除角色及角色关联菜单错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}

	@Override
	public RoleModel findByPrimaryKey(int pId) {
		return roleModelMapper.findByPrimaryKey(pId);
	}

	@Override
	public List<RoleModel> findAll() {
		return roleModelMapper.findAll();
	}

	@Override
	public int findCountOfRname(RolePageQueryParameter pageQueryParameter) {
		return roleModelMapper.findCountOfRname(pageQueryParameter);
	}

	@Override
	public List<RoleModel> findRolesByUserId(UserPageQueryParameter parameter){
		return roleModelMapper.findRolesByUserId(parameter);
	}

	@Override
	public List<ModuleMenuResponse> findAllModuleMenu(){
		List<ModuleMenuQueryResult> list = moduleMenuModelMapper.findAllModuleMenu();
		return getModuleMenuResponseList(list);
	}

	@Override
	public List<ModuleMenuResponse> findModuleMenuByRoleId(Integer roleId){
		//根据角色id查询模块菜单列表
		List<ModuleMenuQueryResult> list = moduleMenuModelMapper.findModuleMenuByRoleId(roleId);
		return getModuleMenuResponseList(list);
	}

	/**
	 * 将list里的ModuleMenuQueryResult转换成ModuleMenuResponse并封装到新的list里
	 * @param list
	 * @return List<ModuleMenuResponse>
	 */
	private List<ModuleMenuResponse> getModuleMenuResponseList(List<ModuleMenuQueryResult> list){
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
	public String addRoleMenu(RoleMenuParameter roleMenuParameter) throws RollbackableBizException{
		//角色id
		Integer roleId = roleMenuParameter.getRoleid();

		//菜单id数组
		String[] menuArray = roleMenuParameter.getMenuidArray();
		
		try {
			//删除角色原有的id
			int num = roleMenuModelMapper.deleteByRoleid(roleId);
			
			if(NullUtil.notNull(menuArray)){
				List<RoleMenuModel> list = new ArrayList<RoleMenuModel>(menuArray.length);
				for (int i = 0; i < menuArray.length; i++) {
					RoleMenuModel model = new RoleMenuModel();
					model.setRef_r_id(roleId);
					model.setRef_mm_id(Integer.valueOf(menuArray[i]));
					list.add(model);
				}
				
				//批量添加
				num = roleMenuModelMapper.insertList(list);
			}
			
			if(num >= 0){
				return Code.SUCCESS;
			}else{
				return Code.FAIL;
			}
		} catch (Exception e) {
			log.error("给角色分配菜单错误:" + e.getMessage());
			throw new RollbackableBizException(e.getMessage());
		}
	}

	@Override
	public PageQueryResult findByPage(RolePageQueryParameter parameter) {
		if(parameter.isPageQuery()){
			int total = roleModelMapper.findCountByPage(parameter);
			if(total > 0){
				List<RoleModel> list = roleModelMapper.findByPage(parameter);
				return new PageQueryResult(list, total);
			}
			return new PageQueryResult();
		} else {
			List<RoleModel> list = roleModelMapper.findByPage(parameter);
			return new PageQueryResult(list, list.size());
		}
	}

//	@Override
//	public RoleModel exist(RolePageQueryParameter parameter) {
//		return roleModelMapper.exist(parameter);
//	}


    @Override
    public UserRoleCheckedVOListResponse queryUserRoleChecked(UserRolePageQueryParameter parameter) {
        UserRoleCheckedVOListResponse listResponse = new UserRoleCheckedVOListResponse();

        RolePageQueryParameter rolePageQueryParameter = new RolePageQueryParameter();
        rolePageQueryParameter.setRef_p_id(parameter.getRef_p_id());//项目ID
        rolePageQueryParameter.setR_status(parameter.getR_status());
        rolePageQueryParameter.setPageQuery(parameter.isPageQuery());
        rolePageQueryParameter.setOrder(parameter.getOrder());
        rolePageQueryParameter.setSort(parameter.getSort());
        rolePageQueryParameter.setOffset(parameter.getOffset());
        rolePageQueryParameter.setLimit(parameter.getLimit());

        PageQueryResult result = this.findByPage(rolePageQueryParameter);
        //全部角色
        List<RoleModel> allRoleList = result.getResultList();

        //关联用户的缓存Map
        Map<Integer, RoleModel> userCache = new HashMap<Integer, RoleModel>();

        //用户ID不为空时，是修改用户操作，需要查询出关联的角色
        if(parameter.getU_id() != null){
            UserPageQueryParameter userPageQueryParameter = new UserPageQueryParameter();
            userPageQueryParameter.setRef_p_id(parameter.getRef_p_id());//项目ID
            userPageQueryParameter.setU_id(parameter.getU_id());//用户ID

            //用户关联的角色
            List<RoleModel> userRoleList = this.findRolesByUserId(userPageQueryParameter);

            if(NullUtil.notNull(userRoleList)){
                for (int i = 0; i < userRoleList.size(); i++) {
                    RoleModel model = userRoleList.get(i);
                    userCache.put(model.getR_id(), model);
                }
            }
        }

        List<UserRoleCheckedVO> voList = new ArrayList<UserRoleCheckedVO>();
        for (int i = 0; i < allRoleList.size(); i++) {
            RoleModel model = allRoleList.get(i);
            UserRoleCheckedVO vo = new UserRoleCheckedVO();
            vo.setR_id(model.getR_id());
            vo.setR_name(model.getR_name());
            vo.setR_status(model.getR_status());
            vo.setR_desc(model.getR_desc());
            if(userCache.get(model.getR_id()) != null){
                vo.setChecked(true);//关联的角色
            }
            voList.add(vo);
        }

        listResponse.setList(voList);
        listResponse.setTotal(voList.size());
        return listResponse;
    }
}


