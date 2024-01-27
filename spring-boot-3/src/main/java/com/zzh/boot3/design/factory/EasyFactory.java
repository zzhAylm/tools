package com.zzh.boot3.design.factory;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 11:46
 */
public class EasyFactory {

    public static Product create(String type) {
        if (type.equals("one")) {
            return new OneProduct();
        } else if (type.equals("two")) {
            return new TwoProduct();
        } else if (type.equals("three")) {
            return new ThreeProduct();
        } else {
            return null;
        }
    }
}
