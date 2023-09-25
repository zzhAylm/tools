package com.zzh.hutool;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.micrometer.common.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/18 15:57
 */
public class HuTool9 {

//
//    public static String getOrderContent(Map<String, Object> requestParam) {
//        Map<String, Object> sortedParams = new TreeMap<String, Object>();
//        if ((requestParam != null) && (requestParam.size() > 0)) {
//            sortedParams.putAll(requestParam);
//        }
//        StringBuilder content = new StringBuilder();
//
//        List<String> keys = new ArrayList<>(sortedParams.keySet());
//        Collections.sort(keys);
//        int index = 0;
//        for (String key : keys) {
//            Object value = sortedParams.get(key);
//            if (!StrUtil.isEmpty(key) && value != null) {
//                if (value instanceof String || value instanceof Integer || value instanceof BigDecimal || value instanceof Boolean || value instanceof Double) {
//                    content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
//                    index++;
//                } else if (value instanceof List) {
//                    List<Object> paramList = new ArrayList<>();
//                    List<Object> vauleList = (List<Object>) value;
//                    for (Object object : vauleList) {
//                        Map<String, Object> paramMap = JSONUtil.toBean(JSONUtil.toJsonStr(object), Map.class);
//                        paramList.add(getOrderContent(paramMap));
//                    }
//                    content.append(index == 0 ? "" : "&").append(key).append("=").append(JSONUtil.toJsonStr(paramList));
//                    index++;
//                }
//            }
//        }
//        return content.toString();
//    }
//

    public static String getOrderContentNew(Map<String, Object> requestParam) {
        Map<String, Object> sortedParams = new TreeMap<String, Object>();
        if ((requestParam != null) && (requestParam.size() > 0)) {
            sortedParams.putAll(requestParam);
        }
        StringBuffer content = new StringBuffer();

        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            Object value = sortedParams.get(key);
            if (!StringUtils.isEmpty(key) && value != null) {
                if (value instanceof String || value instanceof Integer || value instanceof BigDecimal || value instanceof Boolean || value instanceof Double) {
                    content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
                    index++;
                } else if (value instanceof List) {
                    List<Object> paramList = new ArrayList<>();
                    List<Object> vauleList = (List<Object>) value;
                    for (Object object : vauleList) {
                        Map<String, Object> paramMap = JSONUtil.toBean(JSONUtil.toJsonStr(object), Map.class);
                        paramList.add(getOrderContentNew(paramMap));
                    }
                    content.append(index == 0 ? "" : "&").append(key).append("=").append(JSONUtil.toJsonStr(paramList));
                    index++;
                }
            }
        }
        return content.toString();
    }


    public static void main(String[] args) {


        String str="{\n" +
                "    \"code\": \"AMS0000\",\n" +
                "    \"message\": \"结算留存信息查询成功！\",\n" +
                "    \"object\": {\n" +
                "        \"sign\": \"B2qKXOI6HpySVXpGTg0sTlJBViByOleWofi+71JiQeGu5RhVi66xSrewdhZS3aKBpOKu5jrXNlAy9BU0Rd8+Rm6+6iBF5ZwXbsxcH8s4npff1yJYnDw4d3bsp2D+Oe8npSQhPriSZm+asIKjjidQSwrJdc0U8n9MtVQ4AS77NbY=\",\n" +
                "        \"result\": [\n" +
                "            {\n" +
                "                \"businessCode\": null,\n" +
                "                \"acType\": null,\n" +
                "                \"ccy\": \"CNY\",\n" +
                "                \"totalAmt\": 0,\n" +
                "                \"bl\": 0,\n" +
                "                \"smtBlkAmt\": 0,\n" +
                "                \"rcBlkAmt\": 0,\n" +
                "                \"dayBl\": 0,\n" +
                "                \"hisBl\": 0,\n" +
                "                \"acSts\": \"1\",\n" +
                "                \"sbjNo\": \"2181010001\",\n" +
                "                \"adt\": null,\n" +
                "                \"stmDt\": \"20230914\",\n" +
                "                \"cptProp\": \"01\",\n" +
                "                \"rpcId\": null,\n" +
                "                \"rtnAmt\": 0,\n" +
                "                \"bno\": \"00\",\n" +
                "                \"acNo\": \"48368362000202597018\",\n" +
                "                \"lastStmBl\": 0,\n" +
                "                \"usrId\": \"800102154510288\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"businessCode\": null,\n" +
                "                \"acType\": null,\n" +
                "                \"ccy\": \"CNY\",\n" +
                "                \"totalAmt\": 0,\n" +
                "                \"bl\": 0,\n" +
                "                \"smtBlkAmt\": 0,\n" +
                "                \"rcBlkAmt\": 0,\n" +
                "                \"dayBl\": 0,\n" +
                "                \"hisBl\": 0,\n" +
                "                \"acSts\": \"1\",\n" +
                "                \"sbjNo\": \"2181010006\",\n" +
                "                \"adt\": null,\n" +
                "                \"stmDt\": \"20230914\",\n" +
                "                \"cptProp\": \"15\",\n" +
                "                \"rpcId\": null,\n" +
                "                \"rtnAmt\": 0,\n" +
                "                \"bno\": \"00\",\n" +
                "                \"acNo\": \"48368362000202597618\",\n" +
                "                \"lastStmBl\": 0,\n" +
                "                \"usrId\": \"800102154510288\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"timestamp\": \"20230919161139\",\n" +
                "        \"signType\": \"RSA\",\n" +
                "        \"rpcId\": \"a7b5a18502fa45aeb2f48ed7d366bb4a\",\n" +
                "        \"orgId\": \"100001\",\n" +
                "        \"version\": \"1.0\"\n" +
                "    }\n" +
                "}";
        JSONObject bean = JSONUtil.toBean(str, JSONObject.class);
        Map<String,Object> map = bean.get("object", Map.class);
        map.remove("sign");
        System.out.println(getOrderContentNew(map));



    }
}
