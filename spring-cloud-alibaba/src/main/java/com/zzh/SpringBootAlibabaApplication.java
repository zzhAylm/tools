package com.zzh;

import com.zzh.event.MyEventPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Description:
 * @Author: zzh
 * @Crete ${DATE} ${TIME}
 */
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootAlibabaApplication {

    private static final Logger log = LogManager.getLogger(SpringBootAlibabaApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringBootAlibabaApplication.class);
            ApplicationContext context = builder.web(WebApplicationType.SERVLET).run(args);
            String[] activeProfiles = context.getEnvironment().getActiveProfiles();
            System.out.println("ActiveProfiles = " + String.join(",", activeProfiles));
            MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
            publisher.publishEvent();
        } catch (
                Exception e) {// 一定要加此try catch, 方便解决问题
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
