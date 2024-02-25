package com.zzh.config;

import com.zzh.domain.Banana;
import org.springframework.beans.factory.FactoryBean;

public class CherryFactoryBean implements FactoryBean<Banana> {
    @Override
    public Banana getObject() {
        return new Banana();
    }

    @Override
    public Class<?> getObjectType() {
        return Banana.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }



}
