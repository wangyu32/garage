package com.wangyu.garage.vo;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/23 23:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComeinoutVO {

    private StopRecording stopRecording;

    private Garage garage;

}
