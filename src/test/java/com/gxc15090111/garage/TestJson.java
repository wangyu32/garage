//package com.gxc15090111.garage;
//
//import com.alibaba.fastjson.JSON;
//import com.gxc15090111.common.Result;
//import com.gxc15090111.garage.entity.StopRecording;
//import org.json.JSONObject;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Description
// * @Author gxc15090111
// * @Date 2018/12/19 0:50
// */
//public class TestJson {
//
//    public static void main(String[] args) {
//        String json = "{\"success\":true,\"code\":\"0\",\"message\":null,\"data\":{\"id\":18,\"garageid\":1,\"userid\":9,\"carNumber\":null,\"phone\":\"13900090009\",\"intime\":1544951383000,\"outtime\":1545143763067,\"totaltime\":192380067,\"amount\":384760.134,\"status\":1}}";
//
//        Result result = JSON.parseObject(json, Result.class);
//        DateFormat formator = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        if(result.isSuccess()){
//            //将扫描出的信息显示出来
//            StopRecording stopRedcording = userService.convertJSONObjectToStopRecording((JSONObject)result.getData());
//            Date inTime = stopRedcording.getIntime();
//            Date outTime = stopRedcording.getOuttime();
//
//            StringBuffer sb = new StringBuffer();
//            sb.append("状    态：").append("出库成功").append("\n");
//            sb.append("用    户：").append(user.getName()).append("\n");
//            sb.append("手机号码：").append(user.getPhone()).append("\n");
//            sb.append("入库时间：").append(formator.format(stopRedcording.getIntime())).append("\n");
//            sb.append("出库时间：").append(formator.format(stopRedcording.getOuttime())).append("\n");
//            sb.append("停车时间：").append(getDateDiff4Chinese(stopRedcording.getTotaltime())).append("\n");
//            sb.append("停车费用：").append(stopRedcording.getAmount()).append("元").append("\n");
//            String stopRedcordingView = sb.toString();
//            //TODO 更好的显示给前端
//        } else {
//            StringBuffer sb = new StringBuffer();
//            sb.append("状    态：").append("出库失败：").append(result.getMessage()).append("\n");
//            sb.append("用    户：").append(user.getName()).append("\n");
//            sb.append("手机号码：").append(user.getPhone()).append("\n");
//        }
//
//    }
//    private static String getDateDiff4Chinese(long diff) {
//        long nd = 1000 * 24 * 60 * 60;
//        long nh = 1000 * 60 * 60;
//        long nm = 1000 * 60;
//        long ns = 1000 ;
//        // long ns = 1000;
//        // 获得两个时间的毫秒时间差异
////        long diff = endDate.getTime() - nowDate.getTime();
//        // 计算差多少天
//        long day = diff / nd;
//        // 计算差多少小时
//        long hour = diff % nd / nh;
//        // 计算差多少分钟
//        long min = diff % nd % nh / nm;
//
//        long second = diff % nd % nh % ns / nm;
//
//        // 总计多少小时
//        long totalHour = new Double(Math.ceil(diff / nh)).longValue();
//
//        StringBuffer sb = new StringBuffer();
//        sb.append(totalHour).append("小时");
//        sb.append(" (");
//        if(day > 0){
//            sb.append(day).append("天");
//        }
//
//        if(hour > 0){
//            sb.append(hour).append("小时");
//        }
//
//        if(min > 0){
//            sb.append(min).append("分钟");
//        }
//
//        sb.append(second).append("秒");
//
//        sb.append(")");
//        return sb.toString();
//    }
//}
