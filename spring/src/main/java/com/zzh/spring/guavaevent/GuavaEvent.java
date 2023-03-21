package com.zzh.spring.guavaevent;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:33
 */
@Data
@NoArgsConstructor
public class GuavaEvent {

    private String message;

    public GuavaEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GuavaEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
