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
public class OneChainHandler implements ChainHandler{

    private ChainHandler nextChainHandler;

    @Override
    public void handler(Integer num) {
        if (num<=500){
            log.info("OneChainHandler处理");
        }else {
            log.info("无法处理交给下一个人处理");
            nextChainHandler.handler(num);
        }

    }
}
