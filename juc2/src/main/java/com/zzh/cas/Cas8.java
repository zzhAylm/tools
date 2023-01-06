package com.zzh.cas;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/5 22:12
 */
public class Cas8 {

    //

    static AtomicReferenceFieldUpdater<MyVar, Boolean> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

    public void update(MyVar var, Boolean flag) {
        fieldUpdater.getAndSet(var, flag);
    }


    public static void main(String[] args) {
        MyVar var = new MyVar(false);
        fieldUpdater.getAndSet(var, true);
    }

    @Data
    static class MyVar {
        public volatile Boolean isInit = false;

        public MyVar(Boolean isInit) {
            this.isInit = isInit;
        }
    }
}
