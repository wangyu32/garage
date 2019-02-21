package com.gxc15090111.system.model;

/**
 * 用户与角色关联关系Model
 * @author 	gxc15090111	 2018年10月17日 下午6:41:02
 *
 */
public class SysUserRoleModel {
	
	/**
	 * 主键id
	 */
    private Integer ur_id;

    /**
     * 用户id
     */
    private Integer ref_u_id;

    /**
     * 角色id
     */
    private Integer ref_r_id;

    public Integer getUr_id() {
        return ur_id;
    }

    public void setUr_id(Integer ur_id) {
        this.ur_id = ur_id;
    }

    public Integer getRef_u_id() {
        return ref_u_id;
    }

    public void setRef_u_id(Integer ref_u_id) {
        this.ref_u_id = ref_u_id;
    }

    public Integer getRef_r_id() {
        return ref_r_id;
    }

    public void setRef_r_id(Integer ref_r_id) {
        this.ref_r_id = ref_r_id;
    }
}