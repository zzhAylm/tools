package com.zzh.boot3.rpc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/23 20:24
 */
@Slf4j
@SpringBootTest
public class RpcTest {


    @Test
    public void test() {
        WebClient client = WebClient.builder().baseUrl("http://localhost:8080")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 256);
                }).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        WebfluxService webfluxService = factory.createClient(WebfluxService.class);
        webfluxService.webfluxService("zzh", "zzh");
        log.info("rpc test");
    }
}
