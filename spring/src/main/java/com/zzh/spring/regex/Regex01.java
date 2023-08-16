package com.zzh.spring.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/7/12 20:40
 */
public class Regex01 {

    public static void main(String[] args) {
        System.out.println("regex");

        Pattern compile = Pattern.compile("z");

        Matcher matcher = compile.matcher("regexzzh");

        System.out.println(matcher.find());

        System.out.println("zzzh".matches("z"));


        System.out.println(Pattern.matches("z", "zzh"));
    }
}
