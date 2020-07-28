package com.wangyu.controller;

import com.wangyu.common.BaseResponse;
import com.wangyu.common.Code;
import com.wangyu.common.ListResponse;
import com.wangyu.constant.CommonConstants;
import com.wangyu.constant.MessageConstants;
import com.wangyu.constant.SessionAttributeConstants;
import com.wangyu.entity.ModuleMenuTreeNode;
import com.wangyu.entity.RoleUserCountModel;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.DeleteParameter;
import com.wangyu.entity.parameter.RolePageQueryParameter;
import com.wangyu.model.SysModuleMenu;
import com.wangyu.model.SysRole;
import com.wangyu.response.ModuleMenuResponse;
import com.wangyu.response.ModuleMenuTreeNodeListResponse;
import com.wangyu.response.RoleResponse;
import com.wangyu.service.ISysModuleMenuService;
import com.wangyu.service.ISysRoleService;
import com.wangyu.service.ISysUserService;
import com.wangyu.util.NullUtil;
import com.wangyu.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Slf4j
@Controller
@RequestMapping(value = "/sysrole")
public class SysRoleController extends BaseController {
    //对应菜单名
    private static final String MENU_NAME = "操作员角色";

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysModuleMenuService sysModuleMenuService;

    /**
     * 菜单进入
     * @return 跳转页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String list() {
        return "system/sysrole/list";
    }

    /**
     * “编辑”按钮
     * @return 跳转页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String edit(Integer id) {
        if(id != null){
            SysRole roleModel = sysRoleService.getById(id);
            request.setAttribute("model", roleModel);//此处roleModel不包含关联菜单信息
            setSessionAttribute(getSessionKey(roleModel.getId()), roleModel);//缓存到session
        }
        return "system/sysrole/edit";
    }

    /**
     * 查询角色关联的菜单,
     * @param id - 角色ID，r_id为空，是添加时查询；r_id不为空，是修改时查询
     * @return json
     */
    @RequestMapping(value = "/queryrolemenu", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String queryRoleMenu(Integer id) {
        String json = null;
        try {
            ModuleMenuTreeNodeListResponse moduleMenuTreeNodeListResponse =	queryMenuCheckedInfoByRoleId(id);
            json = toJson(moduleMenuTreeNodeListResponse);
            if(id != null){
                //修改时，由于角色关联的菜单与角色基本信息是分开异步请求加载到页面的，所以此处对session中的roleModel再次赋值菜单id数组
//				ModuleMenuTreeNodeListResponse moduleMenuTreeNodeListResponse =	(ModuleMenuTreeNodeListResponse)gson.fromJson(json, ModuleMenuTreeNodeListResponse.class);
                List<String> menuIdList = new ArrayList<String>();
                if(NullUtil.notNull(moduleMenuTreeNodeListResponse.getList())){
                    List<ModuleMenuTreeNode> menuList = moduleMenuTreeNodeListResponse.getList();
                    for (int i = 0; i < menuList.size(); i++) {
                        //模块节点
                        ModuleMenuTreeNode module = menuList.get(i);
                        //菜单节点
                        List<ModuleMenuTreeNode> menus = module.getNodes();
                        if(NullUtil.notNull(menus)){
                            for (int j = 0; j < menus.size(); j++) {
                                ModuleMenuTreeNode menu = menus.get(j);
                                if(menu.getState().isChecked()){
                                    //checked的节点说明是已分配给该角色的
                                    menuIdList.add(menu.getId().split("_")[1]);
                                }
                            }
                        }
                    }
                }

                //session缓存处理
                SysRole roleSession = (SysRole)getSessionAttribute(getSessionKey(id));
//                roleSession.setMm_id_array(menuIdList.toArray(new String[0]));//set菜单节点ID
                setSessionAttribute(getSessionKey(id), roleSession);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return httpError();
        }
        return json;
    }

    /**
     * 保存角色
     * @param roleModel - 角色信息Model
     * @return String
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String save(SysRole roleModel) {
        Integer r_id = roleModel.getId();
        String json = null;
        try {
            if(r_id == null){
                //增加
                RoleResponse response = add(roleModel);
                json = toJson(response);
                //添加成功，记录操作日志
                if(isSuccessResponse(response)){
                    SysRole addRole =	response.getData();
                    roleModel.setId(addRole.getId());
                    //记录日志
//                    addLog(json, MENU_NAME, UserLogTypeConstants.ROLE_ADD, String.valueOf(roleModel.getR_id()), roleModel.getR_name(), toJson(roleModel));
                }
            } else {
                //修改
                BaseResponse baseResponse = update(roleModel);
                json = toJson(baseResponse);
                //修改成功，记录日志
                if(isSuccessResponse(baseResponse)){
                    //从session中读取
                    SysRole modelInSession = (SysRole)getSessionAttribute(getSessionKey(r_id));
                    //记录日志
//                    addLog(null, MENU_NAME, UserLogTypeConstants.ROLE_UPDATE, r_id + "", roleModel.getR_name(), toJson(modelInSession));
                    //从session中移除
                    removeSessionAttribute(getSessionKey(r_id));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return httpError();
        }
        return json;
    }

    /**
     * 批量删除角色，并且删除对应角色与菜单的关联关系
     * @param ids - 批量删除参数
     * @return BaseResponse
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public BaseResponse delete(Integer[] ids) {
        DeleteParameter parameter = new DeleteParameter();
        parameter.setIdArray(ids);
        //校验
        if(NullUtil.isNull(parameter.getIdArray())){
            return renderError(Code.FAIL, MessageConstants.PRIMARY_KEY_CAN_NOT_BE_NULL);
        }

        //查询关联的主播个数
        List<RoleUserCountModel> list = sysRoleService.findRoleUserCount(parameter);

        if(NullUtil.notNull(list)){
            StringBuffer s = new StringBuffer();
            s.append("角色");
            for (int i = 0; i < list.size(); i++) {
                RoleUserCountModel model = list.get(i);
                String name = model.getRName();
                if(i == 0){
                    s.append("[");
                    s.append(name);
                    s.append("]");
                }else{
                    s.append("、[");
                    s.append(name);
                    s.append("]");
                }
            }
            s.append("有关联用户，不能删除！");
            return renderError(Code.FAIL, s.toString());
        }

        String message = null;
        try {
            message = sysRoleService.removeByIds(Arrays.asList(parameter.getIdArray())) == true ? Code.SUCCESS : Code.FAIL;
        } catch (Exception e) {
            return renderError(Code.FAIL);
        }

        if(Code.SUCCESS.equals(message)){
            //记录日志
//            addLog(MENU_NAME, UserLogTypeConstants.ROLE_DELETE, null, null, toJson(parameter.getIdArray()));
            return renderSuccess();
        }else{
            log.error("删除角色信息失败!" );
            return renderError(Code.FAIL);
        }
    }

    /**
     * 分页查询角色信息
     * @param parameter -角色信分页查询参数
     * @return String
     */
    @RequestMapping(value = "/datalist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(RolePageQueryParameter parameter) {
        PageQueryResult result = sysRoleService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;
    }

    /**
     * 根据对象ID获取缓存到Session的key
     * @param id - 实体model的ID
     * @return 缓存到Session的key
     */
    private String getSessionKey(Integer id){
        return SessionAttributeConstants.ROLE_ + id + "_" + getSessionid();
    }

    /**
     * 根据角色id查询角色对应菜单的选择情况：<br>
     * 		1.如果角色id为空，则查询全部菜单并且都设置为未选择状态<br>
     * 		2.如果角色id不为空，则将角色关联的菜单在全部菜单中标记为被选择状态<br>
     * @param r_id - 角色id
     * @return ModuleMenuTreeNodeListResponse
     */
    @RequestMapping(value = "/querymenucheckedinfobyroleid", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    private ModuleMenuTreeNodeListResponse queryMenuCheckedInfoByRoleId(Integer r_id){
        ModuleMenuTreeNodeListResponse listResponse = new ModuleMenuTreeNodeListResponse();

        //全部菜单
        List<ModuleMenuResponse> allRoleMenuList = sysModuleMenuService.findAllModuleMenu();
        //角色关联的菜单
        List<ModuleMenuResponse> roleMenuList = null;
        if(r_id != null){
            roleMenuList = sysModuleMenuService.findModuleMenuByRoleId(r_id);
        } else {
            roleMenuList = new ArrayList<ModuleMenuResponse>();
        }

        //最终勾选了角色关联菜单之后的
        List<ModuleMenuTreeNode> menuList = new ArrayList<ModuleMenuTreeNode>();

        //根据roleMenuList在 allRoleMenuList里勾选已关联的菜单节点
        if(NullUtil.notNull(allRoleMenuList)){

            //角色有分配的菜单
            Map<String, ModuleMenuTreeNode> roleMenuMap = getRoleMenuMap(roleMenuList);

            String id = null;
            String module_key = null;
            String menu_key = null;
            for (int i = 0; i < allRoleMenuList.size(); i++) {
                //模块
                ModuleMenuResponse module = allRoleMenuList.get(i);
                id = module.getMid().toString();
                module_key = CommonConstants.MENU_TYPE_MODULE + "_" + id;

                ModuleMenuTreeNode module_node = roleMenuMap.get(module_key);
                if(module_node != null){
                    module_node.getState().setChecked(true);//勾选
                } else {
                    module_node = new ModuleMenuTreeNode();
                    module_node.setPid(CommonConstants.MENU_ROOT_ID);
                    module_node.setId(module_key);
                    module_node.setText(module.getMname());
                    module_node.setType(CommonConstants.MENU_TYPE_MODULE);
                }

                //子节点
                List<ModuleMenuTreeNode> nodes = new ArrayList<ModuleMenuTreeNode>();
                module_node.setNodes(nodes);
                menuList.add(module_node);

                //菜单
                List<SysModuleMenu> menus = module.getItems();
                if(NullUtil.notNull(menus)){
                    for (int j = 0; j < menus.size(); j++) {
                        SysModuleMenu menu = menus.get(j);
                        id = menu.getId().toString();
                        menu_key = CommonConstants.MENU_TYPE_MENU + "_" + id;//使用"_"分隔
                        ModuleMenuTreeNode menu_node = roleMenuMap.get(menu_key);
                        if(menu_node != null){
                            //只要有一个关联的，模块即勾选
                            menu_node.getState().setChecked(true);//模块勾选
                        } else {
                            menu_node = new ModuleMenuTreeNode();
                            menu_node.setPid(module_key);//父节点
                            menu_node.setId(menu_key);
                            menu_node.setText(menu.getName());
                            menu_node.setType(CommonConstants.MENU_TYPE_MENU);//菜单
                        }
                        module_node.getNodes().add(menu_node);
                    }
                }
            }
        }
        listResponse.setList(menuList);
        return listResponse;
    }

    /**
     * 将角色已分配的菜单缓存成MAP
     * @param list 角色已分配的菜单
     * @return Map
     */
    private Map<String, ModuleMenuTreeNode> getRoleMenuMap(List<ModuleMenuResponse> list) {
        Map<String, ModuleMenuTreeNode> map = new HashMap<String, ModuleMenuTreeNode>();
        if(NullUtil.notNull(list)){
            String id = null;
            String module_key = null;
            String menu_key = null;
            for (int i = 0; i < list.size(); i++) {
                //模块
                ModuleMenuResponse module = list.get(i);
                id = module.getMid().toString();
                module_key = CommonConstants.MENU_TYPE_MODULE + "_" + id;//使用"_"分隔
                ModuleMenuTreeNode module_node = new ModuleMenuTreeNode();
                module_node.setPid(CommonConstants.MENU_ROOT_ID);//根节点
                module_node.setId(module_key);
                module_node.setText(module.getMname());
                module_node.setType(CommonConstants.MENU_TYPE_MODULE);//模块

                map.put(module_key, module_node);

                //菜单
                List<SysModuleMenu> menus = module.getItems();
                if(NullUtil.notNull(menus)){
                    for (int j = 0; j < menus.size(); j++) {
                        SysModuleMenu menu = menus.get(j);
                        id = menu.getId().toString();
                        menu_key = CommonConstants.MENU_TYPE_MENU + "_" + id;//使用"_"分隔

                        ModuleMenuTreeNode node = new ModuleMenuTreeNode();
                        node.setPid(module_key);//父节点
                        node.setId(menu_key);
                        node.setText(menu.getName());
                        node.setType(CommonConstants.MENU_TYPE_MENU);//模块
                        map.put(menu_key, node);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 增加角色
     * @param model - 角色信息Model
     * @return BaseResponse
     */
    private RoleResponse add(SysRole model) {
        //校验
        if(StringUtil.isBlank(model.getName())){
            return new RoleResponse(renderError(Code.FAIL, MessageConstants.ROLE_NAME_NOT_BE_NULL));
        }
        if(model.getStatus() == null){
            return new RoleResponse(renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL));
        }

        RolePageQueryParameter parameter = new RolePageQueryParameter();
        parameter.setName(model.getName());

        //同一项目id对应的角色名称不应该有重复,否则容易混淆
        int count =	sysRoleService.findCountOfRname(parameter);
        if(count > 0){
            return new RoleResponse(renderError(Code.FAIL, MessageConstants.ROLE_NAME_EXIST));
        }

        String message = null;
        try {
            Date date = new Date();
            model.setCreatetime(date);
            model.setUpdatetime(date);
            message = sysRoleService.insertRoleAndRoleMenu(model);
        } catch (Exception e) {
            return new RoleResponse(renderError(Code.FAIL));
        }
        if(Code.SUCCESS.equals(message)){
            RoleResponse response = new RoleResponse();
            response.setMessage(SAVE_SUCCESS);
            SysRole newModel = new SysRole();
            newModel.setId(model.getId());
            response.setData(newModel);
            return response;
        }else{
            log.error("增加角色信息失败!" );
            return new RoleResponse(renderError(Code.FAIL));
        }
    }

    /**
     * 修改角色
     * @param model - 角色信息Model
     * @return BaseResponse
     */
    private BaseResponse update(SysRole model) {
        //校验
        if(model.getId() == null){
            return renderError(Code.FAIL, MessageConstants.PRIMARY_KEY_CAN_NOT_BE_NULL);
        }
        if(StringUtil.isBlank(model.getName())){
            return renderError(Code.FAIL, MessageConstants.ROLE_NAME_NOT_BE_NULL);
        }
        if(model.getStatus() == null){
            return renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL);
        }

        //同一项目id对应的角色名称不应该有重复,否则容易混淆
        RolePageQueryParameter parameter = new RolePageQueryParameter();
        parameter.setId(model.getId());
        parameter.setName(model.getName());
        int count =	sysRoleService.findCountOfRname(parameter);
        if(count > 0){
            return renderError(Code.FAIL, MessageConstants.ROLE_NAME_EXIST);
        }

        String message = null;
        try {
            model.setUpdatetime(new Date());
            message = sysRoleService.updateRoleAndRoleMenu(model);
        } catch (Exception e) {
            return renderError(Code.FAIL);
        }
        if(Code.SUCCESS.equals(message)){
            return renderSuccess();
        }else{
            log.error("修改角色信息失败!" );
            return renderError(Code.FAIL);
        }
    }


}
