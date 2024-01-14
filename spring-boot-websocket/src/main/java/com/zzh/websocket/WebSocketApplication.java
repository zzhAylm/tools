package com.zzh.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/5 10:34
 */
@SpringBootApplication
public class WebSocketApplication {
    private static final Logger log = LogManager.getLogger(WebSocketApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(WebSocketApplication.class);
            ApplicationContext context = builder.web(WebApplicationType.SERVLET).run(args);
            String[] activeProfiles = context.getEnvironment().getActiveProfiles();
            System.out.println("ActiveProfiles = " + String.join(",", activeProfiles));
        } catch (Exception e) {// 一定要加此try catch, 方便解决问题
            // 打印启动失败的错误信息
            e.printStackTrace();
        }
        print();

    }

    private static void print() {
        String sb = """

                                   _ooOoo_
                                  o8888888o
                                  88" . "88
                                  (| -_- |)
                                  O\\  =  /O
                               ____/`---'\\____
                             .'  \\\\|     |//  `.
                            /  \\\\|||  :  |||//  \\\s
                           /  _||||| -:- |||||-  \\\s
                           |   | \\\\\\  -  /// |   |
                           | \\_|  ''\\---/''  |   |
                           \\  .-\\__  `-`  ___/-. /
                         ___`. .'  /--.--\\  `. . __
                      ."" '<  `.___\\_<|>_/___.'  >'"".
                     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |
                     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /
                ======`-.____`-.___\\_____/___.-`____.-'======
                                   `=---='
                ...................................................
                """;
        log.info(sb);
    }

}
