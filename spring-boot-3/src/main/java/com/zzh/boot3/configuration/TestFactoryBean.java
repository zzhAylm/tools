package com.zzh.boot3.configuration;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/5/10 11:07
 */
public class TestFactoryBean implements FactoryBean<FactoryBeanObj> {
    @Override
    public FactoryBeanObj getObject() throws Exception {
        return new FactoryBeanObj();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanObj.class;
    }
}
