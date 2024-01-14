package com.zzh.websocket.configuration;

import com.zzh.websocket.handler.StringWebSocketHandler;
import com.zzh.websocket.proxy.AspectProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/5 10:32
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Resource
    private StringWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/connect").withSockJS();
    }


    @Bean
    public AspectProperties Aspect(){
        return new AspectProperties();
    }
}
