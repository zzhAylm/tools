package com.zzh.controller;

import org.flywaydb.core.Flyway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/5 16:22
 */
@RestController
@RequestMapping("/flyway")
public class FlywayController {

    @RequestMapping("/tes")
    public void flyway() {
        String url = "jdbc:mysql://127.0.0.1:3306/flyway?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "123456";
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
    }

}
