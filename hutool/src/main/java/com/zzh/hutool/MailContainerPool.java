package com.zzh.hutool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// 容器池类
public class MailContainerPool {

    private final List<MailContainer> containers;
//    private int currentIndex;
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    public MailContainerPool(int poolSize) {
        containers = new ArrayList<>();

    }

    public MailContainer getNextContainer() {
        // 使用轮询策略选择下一个容器
        return containers.get(currentIndex.getAndIncrement() % containers.size());
    }

}
