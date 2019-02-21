package com.gxc15090111.system.vo;

/**
 * 用户关联角色的选择Vo
 * @author 	 2018年11月7日
 *
 */
public class SysUserRoleCheckedVO {

	/**
	 * 用户是否关联的角色：true-关联；false-未关联
	 */
	private boolean checked;
	
	/**
	 * 角色id
	 */
    private Integer r_id;
	
	/**
	 * 角色名称
	 */
    private String r_name;
	
	/**
	 * 名称描述
	 */
    private String r_desc;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
    private Integer r_status;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_desc() {
		return r_desc;
	}

	public void setR_desc(String r_desc) {
		this.r_desc = r_desc;
	}

	public Integer getR_status() {
		return r_status;
	}

	public void setR_status(Integer r_status) {
		this.r_status = r_status;
	}
    
}
