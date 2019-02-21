package com.gxc15090111.garage.response;

import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.system.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/24 1:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GarageResponse extends BaseResponse {

    private Garage garage;

}
