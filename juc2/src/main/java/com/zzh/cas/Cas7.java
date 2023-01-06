package com.zzh.cas;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/5 21:45
 */
public class Cas7 {

    //更改BankAccount 非原子类的某个属性，通过反射机制完成的
    static final AtomicIntegerFieldUpdater<BankAccount> updater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    public void add(BankAccount account) {
        updater.getAndIncrement(account);
    }
    //AtomicIntegerFieldUpdater:

    public static void main(String[] args) throws InterruptedException {

        //更新非原子累的某个属性，
        //以一种线程安全的方式操作非线程安全对象内的某些字段

        CountDownLatch countDownLatch=new CountDownLatch(10);
        BankAccount account=new BankAccount();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
//                        account.add();
                        updater.getAndIncrement(account);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println("result="+account.money);
//        AtomicReferenceFieldUpdater

//        AtomicLongFieldUpdater

    }

    @Data
    static class BankAccount {
        String bankName = "CCB";
        String bankNo = "106546545646565";
        String owner = "zs";
        //针对某个非原子类的某个属性，进行原子更新，必须是volatile public类型
        public volatile int money = 0;

        public BankAccount(String bankName, String bankNo, String owner, int money) {
            this.bankName = bankName;
            this.bankNo = bankNo;
            this.owner = owner;
            this.money = money;
        }

        public BankAccount() {
        }



        public void add() {
            money++;
        }


    }


}
