package com.gxc15090111.garage.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/18 17:44
 */
//@Setter
//@Getter
@Component
public class CommonConfig {

//    @Value("projectid")
    public static Integer projectid;

    @Value("${projectid}")
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }
}
