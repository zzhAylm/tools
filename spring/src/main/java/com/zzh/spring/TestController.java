package com.zzh.spring;

import com.google.common.eventbus.EventBus;
import com.zzh.spring.event.VoiceEventPublisher;
import com.zzh.spring.guavaevent.GuavaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:20
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private VoiceEventPublisher publisher;

    @Autowired
    private EventBus eventBus;


    @GetMapping("/{message}")
    public void publisher(@PathVariable String message){
        publisher.publish(message);
    }


    @GetMapping("/guava/{message}")
    public void guava(@PathVariable String message){
        eventBus.post(new GuavaEvent(message));
    }
}
