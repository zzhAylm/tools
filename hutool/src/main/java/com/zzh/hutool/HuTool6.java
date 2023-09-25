package com.zzh.hutool;

import cn.hutool.core.util.StrUtil;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/14 12:16
 */
public class HuTool6 {

    public static void main(String[] args) {
        String str1 = StrUtil.format("|{}\t\t\t\t|{}", "初始化", "1");
        String str2 = StrUtil.format("|{}\t\t\t\t|{}", "初始dsf化", "1");
        String str3 = StrUtil.format("|{}\t\t\t\t|{}", "df", "1");
        String str4 = StrUtil.format("|{}\t\t\t\t|{}", "df", "1");
        String str5 = StrUtil.format("|{}\t\t\t\t|{}", "初dsfs始化", "1");
        String str6 = StrUtil.format("|{}\t\t\t\t|{}", "sdf", "1");
        String str7 = StrUtil.format("|{}\t\t\t\t|{}", "初faf始化", "1");
        String str8 = StrUtil.format("|{}\t\t\t\t|{}", "xx", "1");

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str6);
        System.out.println(str7);
        System.out.println(str8);

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(String.format("%-20s\t\t\t\t%-9s", "标题", "数量")).append("\n");
        stringBuilder.append(String.format("%-20s\t\t\t\t%-10s", "初faf始化", "1")).append("\n");
        stringBuilder.append(String.format("%-20s\t\t\t\t%-10s", "初fasfsff始化", "1")).append("\n");
        stringBuilder.append(String.format("%-20s\t\t\t\t%-10s", "dfadsfa", "1")).append("\n");
        stringBuilder.append(String.format("%-20s\t\t\t\t%-10s", "sf", "1")).append("\n");
        stringBuilder.append(String.format("%-20s\t\t\t\t%-10s", "初sfsfaf始化", "1")).append("\n");
        System.out.println(stringBuilder);


    }
}
