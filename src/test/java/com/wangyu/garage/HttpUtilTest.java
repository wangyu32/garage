package com.wangyu.garage;

import com.wangyu.garage.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/15 18:17
 */
public class HttpUtilTest {

    public static void main(String[] args) throws Exception{
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

}
