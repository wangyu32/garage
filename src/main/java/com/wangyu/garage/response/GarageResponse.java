package com.wangyu.garage.response;

import com.wangyu.garage.entity.Garage;
import com.wangyu.prm.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/24 1:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GarageResponse extends BaseResponse {

    private Garage garage;

}
