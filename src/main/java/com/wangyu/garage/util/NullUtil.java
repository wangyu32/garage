package com.wangyu.garage.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author yu.wang@advance.ai
 * @data 2017/12/27 10:16
 * @Description:
 */
public class NullUtil {


    public static boolean isNull(String s){
        return s == null || s.trim().equals("");
    }

    public static boolean isNotNull(String s){
        return !isNull(s);
    }

    public static boolean isNull(Object s){
        return s == null;
    }

    public static boolean isNotNull(Object s){
        return !isNull(s);
    }


    public static void main(String[] args) {
        NullUtil nu = new NullUtil();
        NullUtil nu1 = null;
        System.out.println(anyNullObject(nu));
        System.out.println(anyNullObject(nu1));
    }

    public static boolean anyNullObject(Object... objects) {
        return Arrays.stream(objects).filter(Objects::isNull).count() > 0;
    }

}
