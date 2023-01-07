package com.zzh.cas;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/6 15:02
 */
public class Cas10 {

    public static void main(String[] args) {


        String str="jdbc:mysql://22.50.6.27:3306/gcl?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";

        String str1="jdbc:oracle:thin:@22.50.100.108:1521/otestdb";
        String str2="jdbc:sqlite::resource:inspection.sqlite";
        System.out.println(str.matches("(^((jdbc:mysql)|(jdbc:oracle)))(.)*"));
        System.out.println(str1.matches("(^((jdbc:mysql)|(jdbc:oracle)))(.)*"));
        System.out.println(str2.matches("(^((jdbc:mysql)|(jdbc:oracle)))(.)*"));


        String ip="22.50.6.27";
        String regex="(.)*"+ip+"(.*)";

        System.out.println(str.matches(regex));
        System.out.println(str1.matches(regex));
        System.out.println(str2.matches(regex));


    }
}
