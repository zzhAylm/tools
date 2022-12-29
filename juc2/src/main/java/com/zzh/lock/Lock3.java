package com.zzh.lock;

/**
 * @Description: 监视器Monitor
 * @Author: zzh
 * @Crete 2022/12/28 21:24
 */
public class Lock3 {

    //每一个Objct都可以座位同步代码块中的锁的对象
    //每一个对象都一个监视器，ObjectMonitor:里面有几个属性：
    //一个指针，指向某个线程的ID，代表这个线程抢到着监视器，重入次数，当锁是否被锁定
    //entryList: 阻塞队列

    //owner：锁定当前对象的线程D
    //count：初始值是 0，表示当前锁对象昱否披锁定，每次执行加锁操作 +1，每次执行解锁操作 -1
    //recursions：初始估是0.表示同步代码块的重入次数
    //Entrvlist：阳毫队列，存放阻瘦的生稈
    //Waitset：等待队列，存放等待的线程
    //每一个对象都对应一个 监视器 ObjectMonitor对象，着就是我们常说的锁
    //monitor对象有几个属性：count，代表是否被锁定，owner：指向线程ID，代表被那个线程持有
    //重入次数
    //阻塞队列
    //等待队列
    public static void main(String[] args) {

        String pattern = "[\\s()\\-+\\d]+";

        String str1 = "fasdf12231a2df";
        String str2 = "1212146";
        String str3 = "86+-18(3489)  5082 4";

        String str4 = "12345678";

        System.out.printf("%s\n", str1.matches(pattern));

        System.out.printf("%s\n", str2.matches(pattern));
        System.out.printf("%s\n", str3.matches(pattern));
        System.out.printf("%s\n", str4.matches(pattern));


    }
}
