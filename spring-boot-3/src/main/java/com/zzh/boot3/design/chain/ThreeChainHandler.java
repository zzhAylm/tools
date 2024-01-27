package com.zzh.boot3.design.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 11:22
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreeChainHandler implements ChainHandler {

    private ChainHandler nextChainHandler;

    @Override
    public void handler(Integer num) {
        if (num <= 5000) {
            log.info("ThreeChainHandler 处理");
        } else {
            log.info("无法处理");
        }

    }
}
