package com.zzh.spring.guavaevent;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:34
 */
@Component
public class GuavaListener {

    @Subscribe
    public void handlerEvent(GuavaEvent guavaEvent){
        System.out.println(guavaEvent.toString());

    }
}
