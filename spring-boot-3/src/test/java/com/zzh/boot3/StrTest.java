package com.zzh.boot3;


import io.micrometer.common.util.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/4/8 15:20
 */
public class StrTest {

    @Test
    public void testStr() {
        String str = "安徽省铜陵市公安局交通警察支队安徽省铜陵市公安局交通警察支队";
        String sub = str.substring(str.length() / 2);
        System.out.println(StringUtils.isEmpty(str.replace(sub, "")));
    }

    @Test
    public void testStr1() {
        String str = "安徽省铜陵市公安局交通警察支队安徽省铜陵市公安局交通警察支队";
        String[] split = Arrays.stream(str.split("队")).map(s -> s + "队").toArray(String[]::new);
        System.out.println(String.join("|", split));

    }

    @Test
    public void repeatStr() {
        String str = "安徽省铜陵市公安局交通警察sdfds支队安徽省铜陵市公安局交通警察支队安徽省铜陵市公fsdsd安局交通警察支队";
        String repeatedSubstring = findRepeatedSubstring(str);
        if (repeatedSubstring != null) {
            System.out.println("重复子串为: " + repeatedSubstring);
        } else {
            System.out.println("没有重复子串");
        }
        System.out.println(getSpiltRegistrationAuthority(str));
    }

    @Test
    public void repeatStr1() {
        String str = "翟笃明/居民身份证/342623197209197115王涛/居民身份证/340825198706233136";
//        String pattern = "([^/]+/居民身份证/\\d+)";
        String pattern = "([^/]+/[^/]+/\\d+)";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(str);
        System.out.println(matcher.groupCount());

        while (matcher.find()) {
            String info = matcher.group(1);
            System.out.println(info);
        }

    }

    @Test
    public void str2() {
        String str = "皖GC1011皖GC1011";
        String pattern = "(\\W\\w{6,7})";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(str);
        System.out.println(matcher.groupCount());
        while (matcher.find()) {
            String info = matcher.group(1);
            System.out.println(info);
        }
    }

    @Test
    public void str3() {
        String str = "2015-01-302015-01-30";
        String pattern = "(\\d{4}-\\d{2}-\\d{2})";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(str);
        System.out.println(matcher.groupCount());
        while (matcher.find()) {
            String info = matcher.group(1);
            System.out.println(info);
        }
    }


    public String getSpiltRegistrationAuthority(String registrationAuthority) {
        if (StringUtils.isNotEmpty(registrationAuthority)) {
            String repeatedSubstring = findRepeatedSubstring(registrationAuthority);
            if (StringUtils.isNotEmpty(repeatedSubstring)) {
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < registrationAuthority.length() / repeatedSubstring.length(); i++) {
                    stringList.add(repeatedSubstring);
                }
                registrationAuthority = String.join("|", stringList);
            } else {
                String[] split = Arrays.stream(registrationAuthority.split("队")).map(s -> s + "队").toArray(String[]::new);
                registrationAuthority = String.join("|", split);
            }
        }
        return registrationAuthority;
    }


    public static String findRepeatedSubstring(String s) {
        int n = s.length();
        for (int length = 1; length <= n / 2; length++) {
            if (n % length == 0) {
                String substr = s.substring(0, length);
                if (repeat(n / length, substr).equals(s)) {
                    return substr;
                }
            }
        }
        return null;
    }

    public static String repeat(int count, String subStr) {
        if (count == 1) {
            return subStr;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(subStr);
        }
        return builder.toString();
    }

    String ipPattern = "^(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2}))$";

    private Pattern pattern = Pattern.compile(ipPattern);

    @Test
    public void ipTest() {
        List<String> ips = List.of("10.0.0.1", "192.168.1.1", "172.16.0.1", "255.255.255.255", "2001:0db8:85a3:0000:0000:8a2e:0370:7334", "2001:db8::1", "fe80::1", "::1");
//        ips.stream().forEach(ip -> {
//            if ((!IPAddressUtil.isIPv4LiteralAddress(ip) && !IPAddressUtil.isIPv6LiteralAddress(ip))) {
//                System.out.println("ip:" + ip);
//            }
//        });

        ips.forEach(ip -> {
            pattern.matcher(ip).matches();

        });
    }

    @Test
    public void testPattern() {
        Pattern compile = Pattern.compile("\\d+");
        Matcher matcher = compile.matcher("1332132asdfadsfs123f");
        System.out.println(matcher.matches());

//        while (matcher.find()){
//            System.out.println(matcher.group());
//        }

//        if (matcher.find(0)){
//            System.out.println(matcher.group(0));
//        }

        if (matcher.find()){
            System.out.println(matcher.group());
        }

        if (matcher.find()){
            System.out.println(matcher.group());
        }

        if (matcher.find()){
            System.out.println(matcher.group());
        }


        if ( matcher.find(0)){
            System.out.println(matcher.group());
        }

    }


}
