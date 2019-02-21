package com.gxc15090111.garage.dto;

import com.gxc15090111.common.validate.ValidateResult;
import com.gxc15090111.garage.enums.SexEnum;
import com.gxc15090111.garage.enums.UserEnum;
import com.gxc15090111.garage.util.NullUtil;
import com.gxc15090111.garage.util.ValidateUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 用户注册DTO
 * @Author wangyu
 * @Date 2018/12/5 1:08
 */
@Data
public class UserRegisterDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 车库ID
     */
    private Long garageId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别0-男;1-女
     */
    private Integer sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String passwordConfirm;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 单价
     */
    private BigDecimal price;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm == null ? null : passwordConfirm.trim();
    }

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validateRegister() {
        if (NullUtil.isNull(phone))
            return ValidateResult.INVALID.setMessage("手机号不能为空");

        if (NullUtil.isNull(name))
            return ValidateResult.INVALID.setMessage("姓名不能为空");

        if (NullUtil.isNull(sex))
            return ValidateResult.INVALID.setMessage("性别不能为空");

        if (NullUtil.isNull(password))
            return ValidateResult.INVALID.setMessage("密码不能为空");

        if (NullUtil.isNull(passwordConfirm))
            return ValidateResult.INVALID.setMessage("确认密码不能为空");

        if (SexEnum.getByCode(sex) == null)
            return ValidateResult.INVALID.setMessage("性别错误");

        if(!password.equals(passwordConfirm)){
            return ValidateResult.INVALID.setMessage("两次输入密码不相同");
        }

        if (phone.length() != 11) {
            return ValidateResult.INVALID.setMessage("手机号码长度错误");
        }

        if (!ValidateUtil.isMobiPhoneNumber(phone)) {
            return ValidateResult.INVALID.setMessage("手机号码格式错误");
        }

        return ValidateResult.VALID;
    }

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validateUpdate() {
        if (NullUtil.isNull(phone))
            return ValidateResult.INVALID.setMessage("手机号不能为空");

        if (NullUtil.isNull(name))
            return ValidateResult.INVALID.setMessage("姓名不能为空");

        if (NullUtil.isNull(sex))
            return ValidateResult.INVALID.setMessage("性别不能为空");

        if (type == null)
            return ValidateResult.INVALID.setMessage("用户类型不能为空");

        if (SexEnum.getByCode(sex) == null)
            return ValidateResult.INVALID.setMessage("性别错误");

        if (UserEnum.getByValue(type) == null)
            return ValidateResult.INVALID.setMessage("用户类型错误");

        if (phone.length() != 11) {
            return ValidateResult.INVALID.setMessage("手机号码长度错误");
        }

        if (!ValidateUtil.isMobiPhoneNumber(phone)) {
            return ValidateResult.INVALID.setMessage("手机号码格式错误");
        }

        return ValidateResult.VALID;
    }

}
