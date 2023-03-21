package com.zzh.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:00
 */
@Component
public class VoiceEventListener implements ApplicationListener<VoiceEvent> {

    @Override
    public void onApplicationEvent(VoiceEvent voiceEvent) {
        // 监听并处理事件
        System.out.println(voiceEvent.toString());
    }
}
