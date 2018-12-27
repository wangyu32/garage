package com.wangyu.garage.result;

import com.wangyu.common.Result;

import java.util.List;

import com.wangyu.garage.entity.GarageItem;

public class GarageItemsResult extends Result {

    List<GarageItem> data;

    @Override
    public List<GarageItem> getData() {
        return data;
    }

    public void setData(List<GarageItem> data) {
        this.data = data;
    }
}
