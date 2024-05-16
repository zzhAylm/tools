package com.zzh.boot3.quartz;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/5/7 17:47
 */
@Slf4j
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(CustomizeJob.class).withIdentity("zzhJob").storeDurably(true).usingJobData("key", "zzhAYlm").build();
    }

    @Bean
    public CronTrigger trigger() throws ParseException {
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression("0/2 * * * * ?");
        cronTrigger.setName("trigger");
        cronTrigger.setJobName("zzhJob");
        return cronTrigger ;
    }

    @Data
    public static class CustomizeJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            Trigger trigger = context.getTrigger();
            JobDetail jobDetail = context.getJobDetail();
            log.info("任务= {}，trigger ={} ,time ={}",jobDetail.getKey(),trigger.getKey(),System.currentTimeMillis());

            log.info("任务 ={}", JSONUtil.toJsonStr(jobDetail.getJobDataMap()));
            log.info("trigger ={}", JSONUtil.toJsonStr(trigger.getJobDataMap()));
        }
    }
}
