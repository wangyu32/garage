package com.wangyu.garage.vo;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.PriceUnit;
import com.wangyu.garage.entity.StopRecording;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/23 23:59
 */
public class ComeinoutVO {

    private StopRecording stopRecording;

    private Garage garage;

    private GarageItem garageItem;

    private PriceUnit priceUnit;

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

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
    }
}
