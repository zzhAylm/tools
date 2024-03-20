package com.zzh.algorithm.dobblepointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description: 滑动窗口
 * @Author: zzh
 * @Crete 2024/3/12 11:35
 */
public class Algorithm13 {

    public static void main(String[] args) {
        var arrayList = new ArrayList<>();

        var list = new ArrayList<>();
        list.add("hello");
        list.add("java 10");
        list.add(10);

        for (var s : list) System.out.println(s);

        char c = 'A';

        System.out.println('z' - 'A');

        Optional<String> department = Optional.empty();
    }


    public String minWindow(String s, String t) {
        String res = "";
        boolean flag = false;
        if (t.length() == 0) {
            return res;
        }
        char[] chars = s.toCharArray();
        char[] array = t.toCharArray();
        int left = 0;
        int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needWin = new HashMap<>();

        for (char c : array) {
            put(needWin, c);
        }
        while (right < chars.length) {
            while (flag(window, needWin) && left < right) {
                res = flag ? (res.length() < (right - left) ? res : s.substring(left, right)) : s.substring(left, right);
                remove(window, chars[left]);
                left++;
                flag = true;
            }
            put(window, chars[right]);
            right++;
        }
        while (flag(window, needWin) && left < right && right <= t.length()) {
            res = flag ? (res.length() < (right - left) ? res : s.substring(left, right)) : s.substring(left, right);
            remove(window, chars[left]);
            left++;
            flag = true;
        }
        return flag ? res : "";
    }

    public void put(Map<Character, Integer> map, Character c) {
        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
        }
    }

    public void remove(Map<Character, Integer> map, Character c) {
        int integer = map.get(c) - 1;
        if (integer == 0) {
            map.remove(c);
        } else if (integer > 0) {
            map.put(c, integer);
        }
    }

    public boolean flag(Map<Character, Integer> window, Map<Character, Integer> needWin) {
        return needWin.entrySet().stream().allMatch(key -> window.containsKey(key) && window.get(key).equals(needWin.get(key)));
    }

}
