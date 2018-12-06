package com.wangyu.garage.service.impl;

import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.mapper.StopRecordingMapper;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.service.StopRecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/3 23:52
 */
@Slf4j
@Service
public class StopRecordingServiceImpl implements StopRecordingService {

    @Autowired
    private StopRecordingMapper stopRecordingMapper;

    @Override
    public int save(StopRecording stopRecording) {
        return stopRecordingMapper.insert(stopRecording);
    }

    @Override
    public int update(StopRecording stopRecording) {
        return stopRecordingMapper.updateByPrimaryKeySelective(stopRecording);
    }

    @Override
    public StopRecording queryStopRecordingByUserId(String userId) {
        return stopRecordingMapper.queryStopRecordingByUserId(userId);
    }

    @Override
    public List<StopRecording> queryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter) {
        return stopRecordingMapper.queryByParameter(stopRecordingQueryParameter);
    }
}
