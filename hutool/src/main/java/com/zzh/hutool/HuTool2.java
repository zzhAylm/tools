package com.zzh.hutool;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.setting.dialect.Props;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/6/7 13:49
 */
public class HuTool2 {



    public static void main(String[] args) {


//        IterUtil.countMap()
        List<String> a = ListUtil.toLinkedList("1", "2", "3");
// 结果: [edit1, edit2, edit3]
//        final LineIter lineIter = new LineIter(ResourceUtil.getUtf8Reader("test_lines.csv"));

//        for (String line : lineIter) {
//            Console.log(line);
//        }

        try {
            //解析标识url
            URI uri = new URI("");

            // 解析标识url，并且提供访问url资源方式
            URL url=new URL("https://www.baidu.com/");

//            URLConnection urlConnection = url.openConnection();
//
//            InputStream inputStream = urlConnection.getInputStream();


            URL url1 = URLUtil.url("https://osscdn.suixingpay.com/0/2/32MDJhYjIyYWZ6N0NhbzJheDIwMjNfMDVfMThfMjFfMjJfMzU0YjQ2ZmM4ODU4NDA2Yjk0NWI5MWQ2ZGZlOTRjNWIyYUtpY21fYnVja2V0MmFiMQ.jpg");

            URI uri1 = url1.toURI();

            System.out.println(uri1);

            // URI 类型
            File file = FileUtil.file(URI.create("file:///path/to/file.txt"));

            System.out.println(file.toString());

        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }


//        IoUtil.getReader()

        Path of = Path.of("/Users/zzh/Desktop", "a.txt");

        FileUtil.touch(of.toFile());

        System.out.println(FileUtil.exist(of.toFile()));


        System.out.println(FileTypeUtil.getType(of.toFile()));

        String str = ResourceUtil.getResourceObj("test.txt").readUtf8Str();


//        String s1 = ResourceUtil.readUtf8Str("test2.txt");

//        System.out.println(ResourceUtil.readUtf8Str("com/zzh/hutool/test3.txt"));

        String s = ResourceUtil.readUtf8Str("/Users/zzh/Desktop/a.txt");

        System.out.println(s);



        Props props = new Props("db.properties");
    }


}
