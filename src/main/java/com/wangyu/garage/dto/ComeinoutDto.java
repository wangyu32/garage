package com.wangyu.garage.dto;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 停车DTO
 * @Author wangyu
 * @Date 2018/12/23 23:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComeinoutDto {

    /**
     * 车库ID
     */
    private Long garageid;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 停车记录
     */
    private Long stopRecordingId;

    public ComeinoutDto(Long garageid, Long userid) {
        this.garageid = garageid;
        this.userid = userid;
    }
}
