package com.zzh.boot3.design;

import com.zzh.boot3.design.chain.OneChainHandler;
import com.zzh.boot3.design.chain.ThreeChainHandler;
import com.zzh.boot3.design.chain.TwoChainHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 11:20
 */
@Slf4j
@SpringBootTest
public class ChainPatternTest {


    // 责任链模式
    @Test
    public void chainPatternTest() {
        OneChainHandler oneChainHandler = new OneChainHandler();
        TwoChainHandler twoChainHandler = new TwoChainHandler();
        ThreeChainHandler threeChainHandler = new ThreeChainHandler();
        oneChainHandler.setNextChainHandler(twoChainHandler);
        twoChainHandler.setNextChainHandler(threeChainHandler);
        oneChainHandler.handler(1000);
    }


}
