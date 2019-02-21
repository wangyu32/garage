package com.gxc15090111.garage.dto;

import com.gxc15090111.common.validate.ValidateResult;
import lombok.Data;

/**
 * @Description 出库DTO
 * @Author wangyu
 * @Date 2018/12/6 17:14
 */
@Data
public class UserComeOutDTO {

    /**
     * 车库ID
     */
    private Long garageId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validate() {
        if(garageId == null)
            return ValidateResult.INVALID.setMessage("车库不能为空");

        if(userId == null)
            return ValidateResult.INVALID.setMessage("用户不能为空");

        return ValidateResult.VALID;
    }

}
