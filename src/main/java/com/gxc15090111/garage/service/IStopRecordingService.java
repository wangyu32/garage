package com.gxc15090111.garage.service;

import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.dto.ComeinoutDTO;
import com.gxc15090111.garage.entity.GarageItem;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.parameter.StopRecordingQueryParameter;
import com.gxc15090111.garage.parameter.UserStopRecordingQueryParameter;
import com.gxc15090111.garage.vo.ComeinoutVO;
import com.gxc15090111.garage.vo.UserStopRecordingVO;

import java.util.List;

/**
 * @Description
 * @Author gxc15090111
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
    ComeinoutVO carComein(ComeinoutDTO comeinoutDto);

    /**
     * 提车出库
     * @param comeinoutDto
     * @return
     */
    ComeinoutVO carComeout(ComeinoutDTO comeinoutDto);

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

    /**
     * 获取一个随机可用的车位
     * @param garageid
     * @return
     */
    GarageItem getRandomAvailableGarageItem(Long garageid);

    /**
     * 查询已扫描入库记录
     * @param comeinoutDto
     * @param stopRecording
     * @return
     */
    ComeinoutVO queryComeInReocrd(ComeinoutDTO comeinoutDto, StopRecording stopRecording);
}
