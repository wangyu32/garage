package com.wangyu.garage.service;

import com.wangyu.garage.entity.StopRecording;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/3 23:51
 */
public interface StopRecordingService {

    int save (StopRecording stopRecording);

    int update (StopRecording stopRecording);

    /**
     * 查询停车记录
     * @param userId
     */
    StopRecording queryStopRecordingByUserId(String userId);
}
