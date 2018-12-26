package com.wangyu.garage.vo;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.StopRecording;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/23 23:59
 */
public class ComeinoutVO {

    private StopRecording stopRecording;

    private Garage garage;

    private GarageItem garageItem;

    public StopRecording getStopRecording() {
        return stopRecording;
    }

    public void setStopRecording(StopRecording stopRecording) {
        this.stopRecording = stopRecording;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public GarageItem getGarageItem() {
        return garageItem;
    }

    public void setGarageItem(GarageItem garageItem) {
        this.garageItem = garageItem;
    }
}
