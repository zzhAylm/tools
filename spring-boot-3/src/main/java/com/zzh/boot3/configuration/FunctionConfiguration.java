package com.zzh.boot3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;

/**
 * @Description: 函数式接口
 * @Author: zzh
 * @Crete 2024/1/20 17:05
 */
@Configuration
public class FunctionConfiguration {


    @Bean
    public RouterFunction<ServerResponse> userFunction() {
        return RouterFunctions.route()
                .GET("/user/{userId}", RequestPredicates.accept(MediaType.ALL), request -> ServerResponse.ok().build())
                .DELETE("/user/{userid}", RequestPredicates.accept(MediaType.ALL), request -> ServerResponse.ok().build())
                .build();
    }
}
