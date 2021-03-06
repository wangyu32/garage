package com.wangyu.garage.dto;

import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.util.NullUtil;
import lombok.Data;

/**
 * @Description 用户查询DTO
 * @Author wangyu
 * @Date 2018/12/6 17:14
 */
@Data
public class UserQueryDTO {

    /**
     * 车库ID
     */
    private Long garageId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validate() {
//        if(garageId == null)
//            return ValidateResult.INVALID.setMessage("车库不能为空");
//
//        if(userId == null)
//            return ValidateResult.INVALID.setMessage("用户不能为空");

        return ValidateResult.VALID;
    }


    /**
     * 校验参数
     * @return
     */
    public ValidateResult validatePhone() {
        if(NullUtil.isNull(phone))
            return ValidateResult.INVALID.setMessage("用户手机号不能为空");

        return ValidateResult.VALID;
    }

}
