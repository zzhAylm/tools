package com.zzh.algorithm;

/**
 * @Description: 重复的子字符串
 * @Author: zzh
 * @Crete 2023/9/4 19:36
 */
public class Algorithm12 {
    public static void main(String[] args) {

        String str = "abcabcabcabc";

        System.out.println(sonStr(str));

        System.out.println(repeatedSubstringPattern1("aabaaba"));
    }

    public static String sonStr(String str) {
        if (str == null || str.length() % 2 == 1) {
            return str;
        }
        String s = (str + str).substring(1, 2 * str.length() - 1);
        if (s.contains(str)) {
            return sonStr(str.substring(0, (str.length()) / 2));
        }
        return str;
    }

    public static boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        String str = (s + s).substring(1, 2 * s.length() - 1);
        return str.contains(s);
    }

    public static boolean repeatedSubstringPattern1(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            String str1 = s.substring(0, s.length() - i);
            String str2 = s.substring(i);
            if (str1.equals(str2)) {
                return s.substring(0, i).equals(s.substring(s.length() - i));
            }
        }
        return false;
    }

}
