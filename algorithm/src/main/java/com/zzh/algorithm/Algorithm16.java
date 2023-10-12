package com.zzh.algorithm;

import cn.hutool.http.HttpUtil;

import java.util.HashMap;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/26 16:55
 */
public class Algorithm16 {

    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("positionInfo", "2|192.168.124.28|||||62D0|00000038||867984060146450|1|1");
        paramMap.put("sysId", "zzh");
        String post = HttpUtil.post("http://localhost:8080/geo/position", paramMap);
        System.out.println(post);
    }
}
