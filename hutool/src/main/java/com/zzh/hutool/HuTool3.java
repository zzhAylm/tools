package com.zzh.hutool;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/7/26 10:04
 */
public class HuTool3 {

    public static void main(String[] args) {

//        Hashtable<>

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


//
//        String message2 = "\"{\\\"groups\\\":\\\"zzh\\\",\\\"noticeName\\\":\\\"wxGroup\\\",\\\"content\\\":\\\"您有一笔二手车付款订单失败，请及时关注:\n商户名称:澧县新宇计算机有限责任公司\n随行付外编: 600600010985106\n随行付内编: 600600010985106\\\",\\\"appName\\\":\\\"zzh\\\",\\\"appSecret\\\":\\\"6c0cbbb5963c4000a056f5e737467f93\\\",\\\"msgtype\\\":\\\"text\\\"}\"";
//        String unescapedMessage2 = StringEscapeUtils.unescapeJava(message2);
//
//        String substring = unescapedMessage2.substring(1, unescapedMessage2.length() - 1);
//        System.out.println(substring);
//        JSONObject jsonObject = JSONUtil.toBean(substring,JSONObject.class);
//        System.out.println(substring);
//        System.out.println(jsonObject);
////
//
//        String message = "{\"groups\":\"zzh\",\"noticeName\":\"wxGroup\",\"content\":\"您有一笔二手车付款订单失败，请及时关注:\\n商户名称:澧县新宇计算机有限责任公司\\n随行付外编: 600600010985106\\n随行付内编: 600600010985106\",\"appName\":\"zzh\",\"appSecret\":\"6c0cbbb5963c4000a056f5e737467f93\",\"msgtype\":\"text\"}";
//
////        String x = StringEscapeUtils.unescapeJava(message);
////        System.out.println(x);
//        JSONObject xObject = JSONUtil.toBean(message,JSONObject.class);
//        System.out.println(xObject);
//
//        String str1="{\"groups\":\"zzh\",\"noticeName\":\"wxGroup\",\"content\":\"您有一笔二手车付款订单失败，请及时关注:\\n商户名称:澧县新宇计算机有限责任公司\\n随行付外编: 600600010985106\\n随行付内编: 600600010985106\",\"files\":null,\"appName\":\"zzh\",\"appSecret\":\"6c0cbbb5963c4000a056f5e737467f93\",\"msgtype\":\"text\",\"toUsers\":null,\"send\":null}";
//
//        String jsonString = StringEscapeUtils.unescapeJava(str1);
//        System.out.println(jsonString);
//        JSONObject jsonObject = JSONUtil.toBean(jsonString, JSONObject.class);
//
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.getStr("content"));
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
//
//        System.out.println(DateUtil.dayOfWeek(DateUtil.date()));
//        System.out.println(DateUtil.thisDayOfMonth());
//
//
//        ArrayList<String> objects = new ArrayList<>();
//
//        System.out.println(objects.get(2));

        String context="[{\"影响账户类型\":[\"00\"],\"影响业务程度\":\"\",\"计划结束时间\":\"2023-08-11T15:10:19\",\"影响业务渠道\":\"网联渠道\",\"影响业务类型\":[\"0000\"],\"状态标识\":\"新增\",\"指定联系方式\":\"\",\"计划开始时间\":\"2023-08-10T15:10:19\",\"信息类型\":\"生产上线\",\"建议操作列表\":[\"关闭渠道\"],\"补充说明\":\"\",\"成员单位名称\":\"网联清算有限公司\",\"超时计划处理方式\":\"\"}]";


//        JSONObject jsonObject = JSONUtil.toBean("", JSONObject.class);

        System.out.println(JSONUtil.isTypeJSONObject(context));


        System.out.println("xxxxxxxx.operties".matches("(.)+(\\.(gradle|properties))$"));

    }
}
