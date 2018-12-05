package com.wangyu.garage.dto;

import com.wangyu.garage.common.ValidateResult;
import com.wangyu.garage.util.NullUtil;
import com.wangyu.garage.util.ValidateUtil;
import lombok.Data;

/**
 * @Description 登录DTO
 * @Author wangyu
 * @Date 2018/12/6 0:45
 */
@Data
public class LoginDTO {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

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

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validate() {
        if (NullUtil.isNull(phone))
            return ValidateResult.INVALID.setMessage("手机号不能为空");

        if (NullUtil.isNull(password))
            return ValidateResult.INVALID.setMessage("密码不能为空");

        if (phone.length() != 11) {
            return ValidateResult.INVALID.setMessage("手机号码长度错误");
        }

        if (!ValidateUtil.isMobiPhoneNumber(phone)) {
            return ValidateResult.INVALID.setMessage("手机号码格式错误");
        }

        return ValidateResult.VALID;
    }
}
