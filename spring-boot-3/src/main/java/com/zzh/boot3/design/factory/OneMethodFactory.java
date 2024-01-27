package com.zzh.boot3.design.factory;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 11:52
 */
public class OneMethodFactory  implements MethodFactory{
    @Override
    public Product create() {
        return new OneProduct();
    }
}
