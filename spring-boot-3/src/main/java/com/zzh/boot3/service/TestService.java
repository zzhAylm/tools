package com.zzh.boot3.service;

import com.zzh.boot3.event.ApplicationEventAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 18:17
 */
@Slf4j
@Service
public class TestService {


    public void test(){
      log.info("test method");
    }


    @EventListener
    public void eventListener(ApplicationEventAware applicationEventAware){
        log.info("收到事件->{}", applicationEventAware);
    }
}
