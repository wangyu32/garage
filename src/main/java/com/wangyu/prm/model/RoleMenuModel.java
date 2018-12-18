package com.wangyu.prm.model;

/**
 * 角色与菜单关联关系Model
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午6:40:00
 *
 */
public class RoleMenuModel {
	
	/**
	 * 主键id
	 */
    private Integer rm_id;

    /**
     * 角色id
     */
    private Integer ref_r_id;

    /**
     * 菜单id
     */
    private Integer ref_mm_id;

    public Integer getRm_id() {
        return rm_id;
    }

    public void setRm_id(Integer rm_id) {
        this.rm_id = rm_id;
    }

    public Integer getRef_r_id() {
        return ref_r_id;
    }

    public void setRef_r_id(Integer ref_r_id) {
        this.ref_r_id = ref_r_id;
    }

    public Integer getRef_mm_id() {
        return ref_mm_id;
    }

    public void setRef_mm_id(Integer ref_mm_id) {
        this.ref_mm_id = ref_mm_id;
    }
}