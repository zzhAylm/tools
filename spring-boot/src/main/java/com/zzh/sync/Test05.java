package com.zzh.sync;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/17 16:58
 */
public class Test05 {

    public static void main(String[] args) {
        String a = "欢迎你！！";
        try {
            //编码（后端传给前端数据之前要先 URLEncoder）
            String b = URLEncoder.encode(a, "UTF-8");
            System.out.println(b);
            //解码（前端传给后端数据，后端接收后要先 URLDecoder）
            String c= URLDecoder.decode(b, "UTF-8");
            System.out.println(c);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
