package com.zzh.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info("收到自定义事件MyEvent");
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onTransactionChange(ApplicationEvent event){
        logger.info("监听到事务提交事件");
    }
}
