package com.wangyu.garage.service;

import com.github.pagehelper.PageInfo;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;

import java.util.List;

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

    /**
     * 根据查询参数查询
     * @param stopRecordingQueryParameter
     * @return
     */
    List<StopRecording> queryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter);

    /**
     * 分页查询停车记录
     * @param stopRecordingQueryParameter
     * @return
     */
    PageInfo<StopRecording> pageQueryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter);
}
