package com.zzh.juc.sychronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/18 23:26
 */
public class SaleTicket {

    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();

        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        },"C").start();
    }

    static class Ticket {
        private Integer saleNum=100;

        public synchronized void sale() {
            if (saleNum > 0) {
                System.out.printf("线程买了一张票%s,还剩%s张票\n", Thread.currentThread().getName(), --saleNum);
            }
        }
    }

    static class Ticket1 {
        //非公平锁
//        private final Lock lock=new ReentrantLock();

        //公平锁
        private final Lock lock=new ReentrantLock(true);
        private Integer saleNum=100;
        public  void sale() {
            lock.lock();
            try {
                if (saleNum > 0) {
                    System.out.printf("线程买了一张票%s,还剩%s张票\n", Thread.currentThread().getName(), --saleNum);
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
