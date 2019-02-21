package com.gxc15090111.garage.dto;

import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.entity.User;
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
public class ComeinoutDTO {

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

    public ComeinoutDTO(Long garageid, Long userid) {
        this.garageid = garageid;
        this.userid = userid;
    }
}
