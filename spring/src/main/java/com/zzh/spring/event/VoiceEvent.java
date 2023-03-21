package com.zzh.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 20:55
 */
public class VoiceEvent extends ApplicationEvent {

    private  String message;

    public VoiceEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VoiceEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
