package com.zzh.algorithm.other;

/**
 * @Description: KMP算法
 * @Author: zzh
 * @Crete 2023/8/22 09:51
 */
public class Algorithm03 {

    public static void main(String[] args) {

//        String str = "aabaacaaddbbccaabaac";
//        int[] nextArr = getNextArr(str.toCharArray());
//        Arrays.stream(nextArr).forEach(System.out::println);
        System.out.println(kmp("cgfafacaabbacdefaaghijklaabbacaccddaaffeeaabbxxaacfafaasfasdffdsaabbaa", "aabbacdefaa"));

    }

    public static boolean kmp(String str, String p) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (p == null || p.length() == 0) {
            return true;
        }
        int[] next = getNextArr(p.toCharArray());
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            while (str.charAt(i) != p.charAt(index) && index > 0) {
                index = next[index - 1];
            }
            index++;
            if (index == p.length()) {
                return true;
            }
        }
        return false;
    }

    public static int[] getNextArr(char[] charArr) {
        int[] next = new int[charArr.length];
        next[0] = 0;
        for (int i = 1; i < charArr.length; i++) {
            if (charArr[i] == charArr[next[i - 1]]) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }
}
