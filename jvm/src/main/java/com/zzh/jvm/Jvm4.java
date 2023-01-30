package com.zzh.jvm;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @Description: 启动类加载器/引导类加载器/bootstrap classloader
 * @Author: zzh
 * @Crete 2023/1/28 18:40
 */
public class Jvm4 {

    // 启动类加载器/引导类加载器(bootstrap classloader) 是有c和c++语言来实现的,加载核心类库，jre/lib/rt.jar、resources.jar 、sun.boot.class.path路径下的内容

    // 扩展类加载器(Extension classloader): 使用Java语言编写，加载指定目录jre/lib/ext

    // 应用程序类加载器、系统类加载器(Application ClassLoader): 加载自定的类，我们定义的类默认都是由应用程序类加载器加载的

     // 自定义类加载器
    public static void main(String[] args) {
        // 获取bootstrap classloader 类加载的目录


        // 扩展类加载器

        String property = System.getProperty("java.ext.dirs");

        Arrays.stream(property.split(";")).forEach(path-> System.out.println("路径："+path));
    }


    // 自定义的类加载器
    public static class MyClassLoader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }
    public static class MyClassLoader1 extends URLClassLoader{
        public MyClassLoader1(URL[] urls, ClassLoader parent) {
//            defineClass
            super(urls, parent);
        }
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }

    }
}
