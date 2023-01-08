package com.zzh.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 强引用
 * @Author: zzh
 * @Crete 2023/1/7 22:46
 */
public class ThreadLocal4 {

    // 强引用，软引用，弱引用，虚引用
    //
    public static void main(String[] args) throws InterruptedException {
        MyObject myObject=new MyObject();

        System.out.println("gc before, myObject:"+myObject);
        myObject=null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("gc after, myObject:"+myObject);


    }

    static class MyObject{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("清理对象前，执行一些清理操作的方法");
            super.finalize();
        }
    }
}
