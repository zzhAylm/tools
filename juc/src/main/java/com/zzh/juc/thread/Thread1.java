package com.zzh.juc.thread;

/**
 * @Description: synchronized:实现线程间通讯
 * @Author: zzh
 * @Crete 2022/12/20 22:18
 */
public class Thread1 {

    private Integer num=0;

    public synchronized void add() throws InterruptedException {

        //使用while，是为了防止 wait再被唤醒时，还可以进行条件判断
        while (num!=0){
            this.wait();
        }
        num++;
        System.out.println("num--操作，num="+num);
        this.notifyAll();
    }

    public synchronized void desc() throws InterruptedException {
        while (num!=1){
            this.wait();
        }
        num--;
        System.out.println("num++操作，num="+num);
        this.notifyAll();
    }

    /**
     * @Description:
     * @Author: zzh
     * @Crete 2022/12/18 23:23
     */
    public static class Juc1 {

        //线程间通讯
        //while： 拒绝虚假唤醒，防止 wait()方法 第二次不进行判断
        public static void main(String[] args) {

            Thread1 thread1 = new Thread1();

            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread1.add();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "addThread").start();
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread1.desc();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "descThread").start();

        }
    }
}
