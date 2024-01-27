package com.zzh.boot3.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 15:04
 */
@Slf4j
@SpringBootTest
public class YamlUtilTest {


    private static final Yaml yamlParser = new Yaml(new Constructor(LinkedHashMap.class));


    @Test
    public void yamlTest() {

        String str = "application:\n" +
                "  base-package: com.suixingpay.portal\n" +
                "\n" +
                "eureka:\n" +
                "  user:\n" +
                "    name: admin\n" +
                "    password: 123456\n" +
                "  instance:\n" +
                "    prefer-ip-address: true #\n" +
                "    instance-id: ${spring.cloud.client.ipAddress}:${server.port}\n" +
                "    ip-address: 22.50.7.25\n" +
                "    non-secure-port: 10395\n" +
                "  client:\n" +
                "    register-with-eureka: true # 自己注册到注册中心1\n" +
                "    serviceUrl:\n" +
                "      #defaultZone: \"http://${eureka.user.name}:${eureka.user.password}@localhost:8761/eureka/\"\n" +
                "      defaultZone: \"https://${eureka.user.name}:${eureka.user.password}@eureka-dev.suixingpay.com/eureka/\"\n" +
                "\n" +
                "feign:\n" +
                "  client:\n" +
                "    config: # 此配置在FeignClientFactoryBean中使用\n" +
                "      default:\n" +
                "        connectTimeout: 1000 # 连接超时时间\n" +
                "        readTimeout: 4000    # 处理超时时间\n" +
                "        loggerLevel: NONE    # 日志级别，NONE, 不记录日志 (默认)，BASIC, 只记录请求方法和URL以及响应状态代码和执行时间。HEADERS, 记录请求和应答的头的基本信息。FULL, 记录请求和响应的头信息，正文和元数据。\n" +
                "  httpclient:\n" +
                "    enabled: true # FeignRibbonClientAutoConfiguration 和 HttpClientFeignLoadBalancedConfiguration\n" +
                "    maxConnections: 200 # 最大连接数，默认值200\n" +
                "    maxConnectionsPerRoute: 50 # 每个host最大使用连接数，默认值50\n" +
                "    connectionTimeout: 2001 # 连接超时时间，默认值2000\n" +
                "    connectionTimerRepeat: 3000 #默认值3000\n" +
                "    #timeToLive: 900 # 默认存活时间900秒\n" +
                "    #timeToLiveUnit: SECONDS # 存活时间单位，默认秒\n" +
                "    #followRedirects: true\n" +
                "  hystrix:\n" +
                "    enabled: true  # 来源 HystrixFeignConfiguration\n" +
                "# Hystrix断路参数配置\n" +
                "# 超时参数不需要配置，不配置时为0，计算httpclient的超时时间并使用\n" +
                "hystrix:\n" +
                "  command:\n" +
                "    default: #default时为默认配置, 相关参数说明在 HystrixCommandProperties\n" +
                "      fallback:\n" +
                "        enabled: false # 关闭fallback功能（默认值为true）\n" +
                "      execution:\n" +
                "        isolation:\n" +
                "          strategy: SEMAPHORE # 默认使用线程池隔离，建议改用SEMAPHORE\n" +
                "          semaphore:\n" +
                "            maxConcurrentRequests: 300\n" +
                "\n" +
                "# Feign 中Ribbon 相关逻辑，请查看LoadBalancerFeignClient\n" +
                "#{serverId}: 添加本层时，单独配置某一serverId的httpClient参数，相关参数参考 CommonClientConfigKey 只适用于zuul\n" +
                "ribbon:\n" +
                "  ReadTimeout: 4000\n" +
                "  ConnectTimeout: 1000\n" +
                "  MaxTotalConnections: 1000 #连接池最大连接数\n" +
                "  MaxConnectionsPerHost: 50 #每个host最大使用连接数\n" +
                "  OkToRetryOnAllOperations: true\n" +
                "  # 在使用过程中，一般会对超时时间、线程池大小、信号量等进行修改，具体要结合业务进行分析、默认Hystrix的超时时间为1秒，但在实际的运用过程中，发现1秒有些过短，通常会设置为5~10秒左右，\n" +
                "  # 如果配置了Ribbon的时间，其超时时间也需要和Hystrix的时间配合使用，一般情况下Ribbon的时间应短于Hystrix超时时间。\n" +
                "  eager-load:\n" +
                "    # 为了解决第一次访问慢问题\n" +
                "    enabled: true\n" +
                "    clients: sxf-eureka\n" +
                "\n" +
                "spring:\n" +
                "  aop:\n" +
                "    proxyTargetClass: \n" +
                "  datasource:\n" +
                "    druid:\n" +
                "      url: jdbc:mysql://22.50.6.12:3306/fd_portal_dev?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull\n" +
                "      username: fd\n" +
                "      password: 123456\n" +
                "      driver-class-name: com.mysql.jdbc.Driver\n" +
                "      filters: stat,wall\n" +
                "      initialSize: 5\n" +
                "      minIdle: 5\n" +
                "      maxActive: 50\n" +
                "      maxWait: 60000\n" +
                "      timeBetweenEvictionRunsMillis: 60000\n" +
                "      minEvictableIdleTimeMillis: 300000\n" +
                "      maxEvictableIdleTimeMillis:  3000000 # 连接保持50分钟，单位是毫秒,因为公司防火墙长连接保持最长时间为1小时；此参数一定要结合实际情况进行配置。\n" +
                "      validationQuery: select 1 from dual\n" +
                "      testWhileIdle: true\n" +
                "      testOnBorrow: false\n" +
                "      testOnReturn: false\n" +
                "      poolPreparedStatements: true\n" +
                "      maxPoolPreparedStatementPerConnectionSize: 20\n" +
                "      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n" +
                "      stat-view-servlet:\n" +
                "        enabled: true\n" +
                "        allow: '*'\n" +
                "      web-stat-filter:\n" +
                "        enabled: true\n" +
                "        url-pattern: /* #配置被filter作用的url\n" +
                "      aop-patterns: ${application.base-package}.service.*\n" +
                "\n" +
                "  redis:\n" +
                "    host: 22.50.4.195\n" +
                "    port: 6379\n" +
                "    pool:\n" +
                "      maxWait: 1500\n" +
                "      maxActive: 2048\n" +
                "      minIdle: 20\n" +
                "      maxIdle: 200\n" +
                "  mail:\n" +
                "    host: smtphm.qiye.163.com\n" +
                "    port: 25\n" +
                "    username: portal@suixingpay.com\n" +
                "    password: g83zZuTLCCLujD\n" +
                "mybatis:\n" +
                "  # 如果Mapper.java和Mapper.xml在同一个包下，可以不用设置\n" +
                "  #  mapperLocations: classpath:mapper/*.xml\n" +
                "  typeAliasesPackage: ${application.base-package}.domain, ${application.base-package}.po\n" +
                "  configuration:\n" +
                "    cacheEnabled: false\n" +
                "    # 一定要设置，避免个别SQL执行时间过长，把系统搞跨\n" +
                "    default-statement-timeout: 10\n" +
                "\n" +
                "suixingpay:\n" +
                "  log:\n" +
                "    kafka-servers: 22.50.2.212:9092,22.50.2.203:9092,22.50.2.206:9092 # 测试环境地址\n" +
                "  mybatis:\n" +
                "    base-enum-packages: ${application.base-package}.enums\n" +
                "  helpDoc:\n" +
                "    url: http://a.suixingpay.com/mircoservice/cloud/\n" +
                "  portal:\n" +
                "    jvmMetricsUrl: https://metric-dev.suixingpay.com/d/VK05bJpZk/jvm-micrometer?refresh=30s&from=now-30m&to=now&var-application=APPLICATIONNAME&var-instance=INSTANCEID&kiosk=tv&theme=light\n" +
                "    frontDomain: https://fd-portal-dev.suixingpay.com\n" +
                "    sms:\n" +
                "      major: 1000\n" +
                "      sub: 1011\n" +
                "      url: http://172.16.136.123:8080/smsService/sms/send\n" +
                "    email-suffix: \"@suixingpay.com\"\n" +
                "  swagger2:\n" +
                "    title: portal-front-service\n" +
                "    description: portal-front-service\n" +
                "    contact:\n" +
                "      name: matieli\n" +
                "      url: http://vbill.cn\n" +
                "      email: ma_tl@suixingpay.com\n" +
                "    # swagger 需要扫的包\n" +
                "    base-package: ${application.base-package}\n" +
                "    base-path: /**\n" +
                "    exclude-path: /error, ${management.context-path}/**\n" +
                "  kaptcha: # 以下配置如果使用默认值，可以不设置\n" +
                "    #验证码在缓存中的存活时长\n" +
                "    expire: 120\n" +
                "    # 验证码文本字符位数 默认为5\n" +
                "    textproducerCharLength: 4\n" +
                "    #  验证码文本字符内容范围 默认为aAbBcCdDeEfFgG2345678hHjJkKmMLnNpPrRsStTuUvVwWxXyY\n" +
                "    textproducerCharString: 1234567890\n" +
                "  token:\n" +
                "    enabled: true\n" +
                "    single-mode: false\n" +
                "    maxTokensForOneUser: 1000000 # 同一账号允许同时在线用户数，如果超过此值，则会踢掉最先登录用户\n" +
                "    client-repository-types: header\n" +
                "    token-name: auth-token\n" +
                "    path-patterns:\n" +
                "    - /**\n" +
                "    exclude-path-patterns:\n" +
                "    - /open-systemapi/**\n" +
                "    - /system-systemapi/**\n" +
                "    - /captcha/**\n" +
                "    - /bootstrap/**\n" +
                "    - /js/**\n" +
                "    - /css/**\n" +
                "    - /images/**\n" +
                "    - /**/favicon.ico\n" +
                "    - /webjars/**\n" +
                "    - /configuration/**\n" +
                "    - /swagger-resources/**\n" +
                "    - /adminLogin/**\n" +
                "    - /login/**\n" +
                "    - /common/**\n" +
                "    - /swagger*/**\n" +
                "    - /v2/systemapi-docs\n" +
                "    - /sql/**\n" +
                "    - /redis/**\n" +
                "    - /user/**\n" +
                "  transaction:\n" +
                "    required-transaction-attributes: bind*, unBind*\n" +
                "  actuator:\n" +
                "    log-file:\n" +
                "      socketio:\n" +
                "        port: 9095\n" +
                "\n" +
                "security:\n" +
                "  basic:\n" +
                "    enabled: true\n" +
                "    path: /open-api/**, /actuator-ui/**, /swagger-ui.html, /v2/api-docs, /druid/**\n" +
                "  user:\n" +
                "    name: portal-open-user\n" +
                "    password: dev.123456\n" +
                "qywechat:\n" +
                "  enabled: true\n" +
                "  corp-id: \"wx8918a4299cc1b440\"\n" +
                "  corp-secret: \"IaLvxSwrMsr6CpRegL5V13T-AM0Fx7DaIiOVtisvUrs\"\n" +
                "  agent-id: 1000007\n" +
                "gitlab:\n" +
                "  enabled: true\n" +
                "  api-url: \"http://192.168.120.68/api/v4/\"\n" +
                "  private-token: \"TvoJUyhA83HBRhfrMRaz\"\n" +
                "  exclude-user-ids: 1,2\n" +
                "coope:\n" +
                "  authUrl: http://172.16.171.133:8080/oauth/auth";

        Map<String, Object> map = toYaml(str);

        map.forEach((k, v) ->
                log.info("key={},value={}", k, v));
    }

    /**
     * @param content
     * @return
     */
    public static Map<String, Object> toYaml(String content) {
        LinkedHashMap<String, Object> linkedHashMap = yamlParser.loadAs(content, LinkedHashMap.class);
        return getFlattenedMap(linkedHashMap);
    }


    private static Map<String, Object> getFlattenedMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        buildFlattenedMap(result, source, null);
        return result;
    }

    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String path) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            if (StringUtils.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            Object value = entry.getValue();
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Collection<Object> collection = (Collection<Object>) value;
                int count = 0;
                for (Object object : collection) {
                    buildFlattenedMap(result,
                            Collections.singletonMap("[" + (count++) + "]", object), key);
                }
            } else {
                result.put(key, (value != null ? value : ""));
            }
        }
    }
}
