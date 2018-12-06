package com.wangyu.garage.mapper;

import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StopRecordingMapper {
    /**
     *
     * @mbg.generated 2018-12-03
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-03
     */
    int insert(StopRecording record);

    /**
     *
     * @mbg.generated 2018-12-03
     */
    int insertSelective(StopRecording record);

    /**
     *
     * @mbg.generated 2018-12-03
     */
    StopRecording selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-03
     */
    int updateByPrimaryKeySelective(StopRecording record);

    /**
     *
     * @mbg.generated 2018-12-03
     */
    int updateByPrimaryKey(StopRecording record);

    /**
     * 查询停车记录
     * @param userId
     * @return
     */
    StopRecording queryStopRecordingByUserId(String userId);

    /**
     * 根据查询参数查询
     * @param stopRecordingQueryParameter
     * @return
     */
    List<StopRecording> queryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter);
}