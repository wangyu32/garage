package com.wangyu.garage.service;

import com.github.pagehelper.PageInfo;
import com.wangyu.garage.dto.ComeinoutDto;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.parameter.UserStopRecordingQueryParameter;
import com.wangyu.garage.vo.ComeinoutVO;
import com.wangyu.garage.vo.ComeoutVO;
import com.wangyu.garage.vo.UserStopRecordingVO;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/3 23:51
 */
public interface IStopRecordingService {

    /**
     * 报错停车记录
     * @param stopRecording
     * @return
     */
    int save (StopRecording stopRecording);

    /**
     * 修改停车记录
     * @param stopRecording
     * @return
     */
    int update (StopRecording stopRecording);

    /**
     * 入库停车
     */
    ComeinoutVO carComein(ComeinoutDto comeinoutDto);

    /**
     * 提车出库
     * @param comeinoutDto
     * @return
     */
    ComeinoutVO carComeout(ComeinoutDto comeinoutDto);

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

    /**
     * 分页查询用户停车记录
     * @param stopRecordingQueryParameter
     * @return
     */
    PageInfo<UserStopRecordingVO> queryUserStopRecording(UserStopRecordingQueryParameter stopRecordingQueryParameter);
}
