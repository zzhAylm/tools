package com.zzh.boot3.rpc;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/23 21:30
 */
@Service
public interface WebfluxService {

    @GetExchange(url = "/test/test", accept = "application/json")
    void webfluxService(@RequestParam String name, @RequestHeader("Authorization") String auth);
}
