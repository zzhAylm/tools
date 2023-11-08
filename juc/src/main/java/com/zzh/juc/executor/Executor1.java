package com.zzh.juc.executor;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/7 15:26
 */
public class Executor1 {

    public static void main(String[] args) {

//        Executors.
//        Executor executor=new ThreadPoolExecutor()

//        new Thread(()->{
//            System.out.println("111111");
//        }).start();



        String str="{\"code\":\"AMS0000\",\"message\":\"结算留存信息查询成功！\",\"object\":{\"rpcId\":null,\"result\":[{\"acNo\":\"48368362000179839018\",\"usrId\":\"800102370110816\",\"businessCode\":null,\"acType\":null,\"ccy\":\"CNY\",\"totalAmt\":2178397.2,\"bl\":2167500.5,\"smtBlkAmt\":10896.7,\"rcBlkAmt\":0,\"dayBl\":0,\"hisBl\":2167500.5,\"acSts\":\"1\",\"sbjNo\":\"2181010001\",\"adt\":null,\"cptProp\":\"01\",\"stmDt\":\"20231107\",\"rtnAmt\":10.0},{\"acNo\":\"48368362000179839618\",\"usrId\":\"800102370110816\",\"businessCode\":null,\"acType\":null,\"ccy\":\"CNY\",\"totalAmt\":1553.01,\"bl\":1553,\"smtBlkAmt\":0.01,\"rcBlkAmt\":0,\"dayBl\":0,\"hisBl\":1553,\"acSts\":\"1\",\"sbjNo\":\"2181010006\",\"adt\":null,\"cptProp\":\"15\",\"stmDt\":\"20231107\",\"rtnAmt\":10.0}]}}@@@";
        int i = str.lastIndexOf("@@@");
        String[] split = str.split("@@@", i);
        System.out.println(split[0]);
        System.out.println(split[1]);
        System.out.println(i);
    }
}
