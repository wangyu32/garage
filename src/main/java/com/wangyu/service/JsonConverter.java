package com.wangyu.service;

import com.google.gson.Gson;

public interface JsonConverter {

    default String toJson(Object o){
        return new Gson().toJson(o);
    }
}
