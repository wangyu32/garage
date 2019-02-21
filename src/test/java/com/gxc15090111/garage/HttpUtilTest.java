package com.gxc15090111.garage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gxc15090111.common.Result;
import com.gxc15090111.garage.dto.UserComeOutDTO;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.parameter.StopRecordingQueryParameter;
import com.gxc15090111.garage.result.ComeinoutResult;
import com.gxc15090111.garage.result.GarageItemsResult;
import com.gxc15090111.garage.util.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/15 18:17
 */
public class HttpUtilTest {

    public static void main(String[] args) throws Exception{
//        test1();
//        stopRecoding();

//        testComeout();
        queryAllGarageItems();
    }

    private static void queryAllGarageItems() {
        String url = "http://localhost:8081/garage/garage/queryAllGarageItem?garageid=1";
        String json = HttpUtils.get(url);
        GarageItemsResult result = JSON.parseObject(json, GarageItemsResult.class);
        System.out.println(result);
    }

    private static void testComeout() throws Exception {
        UserComeOutDTO userComeOutDTO = new UserComeOutDTO();
        userComeOutDTO.setGarageId(1L);
        userComeOutDTO.setUserId(1L);

        String url = "http://localhost:8081/garage/garage/comein";
//        String url = "http://localhost:8081/garage/garage/comeout";
        String json = HttpUtils.postJson(url, JSON.toJSONString(userComeOutDTO));
        Result result = JSON.parseObject(json, Result.class);

        ComeinoutResult comeinoutResult = JSON.parseObject(json, ComeinoutResult.class);
        System.out.println(comeinoutResult);
    }

    private static void stopRecoding() throws Exception{
        //查询停车记录
        StopRecordingQueryParameter parameter = new StopRecordingQueryParameter();
        parameter.setUserid(1L);
        parameter.setGarageid(1L);
        parameter.setStatus(1);

        String url = "http://localhost:8080/garage/garage/queryStopRecording";

        Map<String, String> map = new HashMap<>();
        map.put("userid", "1");
        map.put("garageid", "1");
        map.put("status", "1");

//        String json = HttpUtils.submitGetData(url, map);
        String json = HttpUtils.get(url, map);
        System.out.println(json);
        Result result = JSON.parseObject(json, Result.class);

        List<StopRecording> list = new ArrayList<>();
        if(result.isSuccess()){
            JSONArray jsonArray = (JSONArray)result.getData();
            for(Object object : jsonArray){
                JSONObject jsonObject = (JSONObject)object;
                list.add(convertJSONObjectToStopRecording(jsonObject));
            }
        }
        System.out.println(list);
    }

    private static void test1() throws Exception{
        String url = "http://localhost:8080/garage/user/login";

        Map<String, String> params = new HashMap<>();
        String postJson = "{\n" +
                "\t\"phone\":\"13012345671\",\n" +
                "\t\"password\":\"王\"\n" +
                "}";
        System.out.println(postJson);
        String json = HttpUtils.postJson(url, postJson);
        System.out.println(json);

        json = HttpUtils.httpPostWithJSON(url, postJson);
        System.out.println(json);
    }

    public static StopRecording convertJSONObjectToStopRecording(JSONObject jsonObject) throws Exception {
        try {
            if (jsonObject == null)
                return null;
            StopRecording bean = new StopRecording();
            bean.setId(jsonObject.getLong("id"));
            bean.setGarageid(jsonObject.getLong("garageid"));
            bean.setUserid(jsonObject.getLong("userid"));
            bean.setCarNumber(jsonObject.getString("carNumber"));
            bean.setPhone(jsonObject.getString("phone"));
            bean.setIntime(jsonObject.getDate("intime"));
            bean.setOuttime(jsonObject.getDate("outtime"));
            bean.setTotaltime(jsonObject.getLong("totaltime"));
            bean.setStatus(jsonObject.getInteger("status"));
            bean.setAmount(jsonObject.getBigDecimal("amount"));
            return bean;
        } catch (Exception e) {
            throw e;
        }
    }
}
