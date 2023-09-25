package com.zzh.hutool;

import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/20 20:53
 */
public class HuTool8 {

    public static void main(String[] args) {


        String str = "{\"acNo\":\"48368362000116155618\",\"usrId\":\"800102158120308\",\"stmDt\":\"20230920\",\"totalAmt\":48919681.37,\"bl\":30832.41,\"smtBlkAmt\":48888848.96,\"rcBlkAmt\":0,\"dayBl\":99.52,\"hisBl\":30732.89,\"acSts\":\"1\",\"sbjNo\":\"2181010001\",\"rtnAmt\":9025.36,\"cptProp\":\"01\",\"ccy\":\"CNY\"}";


        Map<String, Object> map = JSONUtil.toBean(str, Map.class);

        map.forEach((key,value)-> System.out.println("key:"+key+",value:"+value));


    }
}
