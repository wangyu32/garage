package com.wangyu.garage.util;

import org.springframework.util.DigestUtils;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/5 1:19
 */
public class Util extends NullUtil {

    public static String md5(String str){
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(md5("1"));
        //c4ca4238a0b923820dcc509a6f75849b
        //c4ca4238a0b923820dcc509a6f75849b
    }

}
