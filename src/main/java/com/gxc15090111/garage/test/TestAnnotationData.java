package com.gxc15090111.garage.test;

import lombok.Data;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/5 1:43
 */
@Data
public class TestAnnotationData {

    private String name;

    public static void main(String[] args) {
        TestAnnotationData t = new TestAnnotationData();
        t.setName(" asd ");
        System.out.println(t.getName());
    }

}
