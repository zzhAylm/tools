package com.zzh.algorithm;

import cn.hutool.core.date.DateUtil;

import java.util.Calendar;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/12 16:40
 */
public class Algorithm15 {

    public static void main(String[] args) {


//        Calendar calendar =Calendar.getInstance();
//
//        calendar.setTime(DateUtil.parse("2023/8/28"));
//
//        calendar.add(Calendar.HOUR_OF_DAY,21);
//        Long start = calendar.getTimeInMillis();
//        System.out.println(start);
//        calendar.setTime(DateUtil.parse("2023/8/28  21:01:00"));
//        long end = calendar.getTimeInMillis();
//        System.out.println(end);
//        System.out.println(end>start);





        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parse("2023/8/28"));
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Long begin = calendar.getTimeInMillis();
        System.out.println(begin);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Long end = calendar.getTimeInMillis();
        System.out.println(end);



    }

}
