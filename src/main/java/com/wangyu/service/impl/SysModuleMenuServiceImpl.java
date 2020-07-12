package com.wangyu.service.impl;

import com.wangyu.entity.ModuleMenuQueryResult;
import com.wangyu.model.SysModuleMenu;
import com.wangyu.mapper.SysModuleMenuMapper;
import com.wangyu.response.ModuleMenuResponse;
import com.wangyu.service.ISysModuleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Service
public class SysModuleMenuServiceImpl extends ServiceImpl<SysModuleMenuMapper, SysModuleMenu> implements ISysModuleMenuService {

    @Autowired
    private SysModuleMenuMapper sysModuleMenuMapper;

    @Override
    public List<ModuleMenuResponse> findAllModuleMenu() {
        List<ModuleMenuQueryResult> list = sysModuleMenuMapper.findAllModuleMenu();
        return getModuleMenuResponseList(list);
    }

    @Override
    public List<ModuleMenuResponse> findModuleMenuByRoleId(Integer roleId) {
        //根据角色id查询模块菜单列表
        List<ModuleMenuQueryResult> list = sysModuleMenuMapper.findModuleMenuByRoleId(roleId);
        return getModuleMenuResponseList(list);    }

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
            List<SysModuleMenu> itemList = null;
            //临时缓存模块id
            Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
            for (int i = 0; i < list.size(); i++) {
                ModuleMenuQueryResult result = list.get(i);
                //模块id
                moduleId = result.getId();
                if(cache.get(moduleId) == null){
                    cache.put(moduleId, moduleId);
                    //模块信息
                    moduleMenuResult = new ModuleMenuResponse();
                    moduleMenuResult.setMid(result.getId());
                    moduleMenuResult.setMimage(result.getImage());
                    moduleMenuResult.setMname(result.getName());
                    moduleMenuResult.setMorder(result.getOrder());
                    moduleMenuResult.setMtype(result.getType());

                    itemList = new ArrayList<SysModuleMenu>();
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
}
