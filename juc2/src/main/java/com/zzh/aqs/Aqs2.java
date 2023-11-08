package com.zzh.aqs;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: ReentrantLock()
 * @Author: zzh
 * @Crete 2023/1/10 20:51
 */
public class Aqs2 {


    ///
    public static void main(String[] args) throws UnsupportedEncodingException {

        //公平锁和非公平锁的区别：抢占锁的时候，判断当前队列是否有排队的线程
        // 非公平锁，直接cas抢占， 公平锁会先判断一下队列中是否与线程在排队

        // ReentrantLock ：
        // CountDownLatch:
        // sys 栅栏
        // seph 信号两  底层用的都是AQS
//        Lock lock=new ReentrantLock();
//        Lock lock1=new ReentrantLock(true);
//
//        //非公平锁
//        lock.lock();
//        lock.unlock();
//
//        // 公平锁
//        lock1.lock();
//        lock1.unlock();
//


        String str = "write#{\"path\":\"/home/app/sxpservice/Workspace/用户_二维码进件服务项目173_149/case/001_存在多份自主签署和静默签署_最新自主签署时间等于最新静默签署时间_最新自主签署创建时间大于最新静默签署创建时间_返回自主签署时间最新记录且标识为02.case\",\"appendFlag\":\"false\",\"uuid\":\"865a2e30-9cd1-49ac-814f-dfba175fc77a\",\"content\":\"def initScript0 = \\\"\\\"\\\"delete from ums.t_ums_elec_signature where mno = '836871648165156';;\\nINSERT INTO UMS.T_UMS_ELEC_SIGNATURE\\n(UUID, MNO, ELEC_SIGN_TYPE, FILE_NAME, USER_ALIAS, RESP_CODE, RESP_MSG, CREATE_TIME, UPDATE_TIME, RETRY_COUNT, PARAM_CONTENT, ELEC_CATEGORY, ELEC_VERSION, EFFECTIVE_DATE, EXPIRE_DATE)\\nVALUES('2cdb764814f4455da1f5e0c51befe0bb', '836871648165156', '00', 'UMS000001988611088.pdf', 'glcs20211116000722', '0000', '成功', TIMESTAMP '2023-01-09 11:25:17.000000', TIMESTAMP '2023-01-10 16:48:36.000000', 0, '{\\\"cprRegAddr\\\":\\\"江西省-赣州市-南康区-东山街道办事处河边街\\\",\\\"mecOnlineTyp\\\":\\\"线下\\\",\\\"fileName\\\":\\\"UMS000001988611088.pdf\\\",\\\"startMonth\\\":\\\"01\\\",\\\"debitRate\\\":\\\"0.55%\\\",\\\"legPerCrdTyp\\\":\\\"身份证\\\",\\\"jiaLinkMan\\\":\\\"谢启华\\\",\\\"signDate\\\":\\\"20230110\\\",\\\"endYear\\\":\\\"2024\\\",\\\"elecSignStart\\\":\\\"20230110\\\",\\\"jiaAccountNum\\\":\\\"6236682120001408033\\\",\\\"jiaName\\\":\\\"江西闽赣茶业有限公司\\\",\\\"ulWechatRate\\\":\\\"0.31%\\\",\\\"operLicNo\\\":\\\"360782210008986\\\",\\\"elecSignVersion\\\":\\\"7.0\\\",\\\"userAlias\\\":\\\"glcs20211116000722\\\",\\\"endDay\\\":\\\"09\\\",\\\"legPerCrdNo\\\":\\\"352229197712136510\\\",\\\"jiaMobile\\\":\\\"18514789854\\\",\\\"startTime\\\":\\\"20230110\\\",\\\"ulAlipayRate\\\":\\\"0.32%\\\",\\\"posPage\\\":\\\"11\\\",\\\"mecType\\\":\\\"企业\\\",\\\"cprRegNmCn\\\":\\\"江西闽赣茶业有限公司\\\",\\\"legPerNm\\\":\\\"谢启华\\\",\\\"jiaBankNum\\\":\\\"谢启华\\\",\\\"creditRate\\\":\\\"0.6%\\\",\\\"startDay\\\":\\\"10\\\",\\\"bnkNm\\\":\\\"中国建设银行\\\",\\\"startYear\\\":\\\"2023\\\",\\\"jiaBank\\\":\\\"中国建设银行\\\",\\\"operScope\\\":\\\"茶叶、预包装食品批发、零售；卷烟销售（依法须经批准的项目，经相关部门批准后方可开展经营活动）※\\\",\\\"busAddr\\\":\\\"宁夏回族自治区-银川市-金凤区-装机地址1号\\\",\\\"debitMaxFee\\\":\\\"25.0\\\",\\\"ulUnionRate\\\":\\\"\\\",\\\"posY\\\":\\\"425\\\",\\\"actNm\\\":\\\"谢启华\\\",\\\"actNo\\\":\\\"6236682120001408033\\\",\\\"settleCycle\\\":\\\"T1\\\",\\\"endMonth\\\":\\\"01\\\",\\\"localFilePath\\\":\\\"/home/app/elecsignature/PDF/20230110/UMS000001988611088.pdf\\\"}', '00', '7.0', TIMESTAMP '2023-01-10 00:00:00.000000', TIMESTAMP '2024-01-09 00:00:00.000000');;\\n\\ndelete from UMS.T_UMS_ELEC_SIGNATURE_LOG where mno = '836871648165156';;\\nINSERT INTO UMS.T_UMS_ELEC_SIGNATURE_LOG\\n(UUID, ELEC_SIGNATURE_UUID, MNO, ELEC_SIGN_TYPE, FILE_NAME, USER_ALIAS, RESP_CODE, RESP_MSG, CREATE_TIME, UPDATE_TIME, RETRY_COUNT, PARAM_CONTENT, ELEC_CATEGORY, ELEC_VERSION, EFFECTIVE_DATE, EXPIRE_DATE)\\nVALUES('74a96cba707444d29167e2f82fc8f421', '2cdb764814f4455da1f5e0c51befe0bb', '836871648165156', '00', 'UMS000001117252548.pdf', 'glcs20211116000722', '0000', '成功', TIMESTAMP '2023-01-09 11:25:17.000000', TIMESTAMP '2023-01-10 16:48:24.000000', 0, '{\\\"cprRegAddr\\\":\\\"江西省-赣州市-南康区-东山街道办事处河边街\\\",\\\"mecOnlineTyp\\\":\\\"线下\\\",\\\"fileName\\\":\\\"UMS000001117252548.pdf\\\",\\\"startMonth\\\":\\\"01\\\",\\\"debitRate\\\":\\\"0.55%\\\",\\\"legPerCrdTyp\\\":\\\"身份证\\\",\\\"jiaLinkMan\\\":\\\"谢启华\\\",\\\"signDate\\\":\\\"20230110\\\",\\\"endYear\\\":\\\"2024\\\",\\\"elecSignStart\\\":\\\"20230110\\\",\\\"jiaAccountNum\\\":\\\"6236682120001408033\\\",\\\"jiaName\\\":\\\"江西闽赣茶业有限公司\\\",\\\"ulWechatRate\\\":\\\"0.31%\\\",\\\"operLicNo\\\":\\\"360782210008986\\\",\\\"elecSignVersion\\\":\\\"7.0\\\",\\\"userAlias\\\":\\\"glcs20211116000722\\\",\\\"endDay\\\":\\\"09\\\",\\\"legPerCrdNo\\\":\\\"352229197712136510\\\",\\\"jiaMobile\\\":\\\"18514789854\\\",\\\"startTime\\\":\\\"20230110\\\",\\\"ulAlipayRate\\\":\\\"0.32%\\\",\\\"posPage\\\":\\\"11\\\",\\\"mecType\\\":\\\"企业\\\",\\\"cprRegNmCn\\\":\\\"江西闽赣茶业有限公司\\\",\\\"legPerNm\\\":\\\"谢启华\\\",\\\"jiaBankNum\\\":\\\"谢启华\\\",\\\"creditRate\\\":\\\"0.6%\\\",\\\"startDay\\\":\\\"10\\\",\\\"bnkNm\\\":\\\"中国建设银行\\\",\\\"startYear\\\":\\\"2023\\\",\\\"jiaBank\\\":\\\"中国建设银行\\\",\\\"operScope\\\":\\\"茶叶、预包装食品批发、零售；卷烟销售（依法须经批准的项目，经相关部门批准后方可开展经营活动）※\\\",\\\"busAddr\\\":\\\"宁夏回族自治区-银川市-金凤区-装机地址1号\\\",\\\"debitMaxFee\\\":\\\"25.0\\\",\\\"ulUnionRate\\\":\\\"\\\",\\\"posY\\\":\\\"425\\\",\\\"actNm\\\":\\\"谢启华\\\",\\\"actNo\\\":\\\"6236682120001408033\\\",\\\"settleCycle\\\":\\\"T1\\\",\\\"endMonth\\\":\\\"01\\\",\\\"localFilePath\\\":\\\"/home/app/elecsignature/PDF/20230110/UMS000001117252548.pdf\\\"}', '00', '7.0', TIMESTAMP '2023-01-09 00:00:00.000000', TIMESTAMP '2024-01-09 00:00:00.000000');;\\nINSERT INTO UMS.T_UMS_ELEC_SIGNATURE_LOG\\n(UUID, ELEC_SIGNATURE_UUID, MNO, ELEC_SIGN_TYPE, FILE_NAME, USER_ALIAS, RESP_CODE, RESP_MSG, CREATE_TIME, UPDATE_TIME, RETRY_COUNT, PARAM_CONTENT, ELEC_CATEGORY, ELEC_VERSION, EFFECTIVE_DATE, EXPIRE_DATE)\\nVALUES('3327ad37fa45409e8493fd2729cba63d', '2cdb764814f4455da1f5e0c51befe0bb', '836871648165156', '00', 'UMS000000398780185.pdf', 'glcs20211116000722', '0000', '成功', TIMESTAMP '2023-01-10 16:46:13.000000', TIMESTAMP '2023-01-10 16:46:13.000000', 0, '{\\\"cprRegAddr\\\":\\\"江西省-赣州市-南康区-东山街道办事处河边街\\\",\\\"mecOnlineTyp\\\":\\\"线下\\\",\\\"fileName\\\":\\\"UMS000000398780185.pdf\\\",\\\"startMonth\\\":\\\"01\\\",\\\"debitRate\\\":\\\"0.55%\\\",\\\"legPerCrdTyp\\\":\\\"身份证\\\",\\\"jiaLinkMan\\\":\\\"谢启华\\\",\\\"signDate\\\":\\\"20230110\\\",\\\"endYear\\\":\\\"2024\\\",\\\"elecSignStart\\\":\\\"20230110\\\",\\\"jiaAccountNum\\\":\\\"6236682120001408033\\\",\\\"jiaName\\\":\\\"江西闽赣茶业有限公司\\\",\\\"ulWechatRate\\\":\\\"0.31%\\\",\\\"operLicNo\\\":\\\"360782210008986\\\",\\\"elecSignVersion\\\":\\\"7.0\\\",\\\"userAlias\\\":\\\"glcs20211116000722\\\",\\\"endDay\\\":\\\"09\\\",\\\"legPerCrdNo\\\":\\\"352229197712136510\\\",\\\"jiaMobile\\\":\\\"18514789854\\\",\\\"startTime\\\":\\\"20230110\\\",\\\"ulAlipayRate\\\":\\\"0.32%\\\",\\\"posPage\\\":\\\"11\\\",\\\"mecType\\\":\\\"企业\\\",\\\"cprRegNmCn\\\":\\\"江西闽赣茶业有限公司\\\",\\\"legPerNm\\\":\\\"谢启华\\\",\\\"jiaBankNum\\\":\\\"谢启华\\\",\\\"creditRate\\\":\\\"0.6%\\\",\\\"startDay\\\":\\\"10\\\",\\\"bnkNm\\\":\\\"中国建设银行\\\",\\\"startYear\\\":\\\"2023\\\",\\\"jiaBank\\\":\\\"中国建设银行\\\",\\\"operScope\\\":\\\"茶叶、预包装食品批发、零售；卷烟销售（依法须经批准的项目，经相关部门批准后方可开展经营活动）※\\\",\\\"busAddr\\\":\\\"宁夏回族自治区-银川市-金凤区-装机地址1号\\\",\\\"debitMaxFee\\\":\\\"25.0\\\",\\\"ulUnionRate\\\":\\\"\\\",\\\"posY\\\":\\\"425\\\",\\\"actNm\\\":\\\"谢启华\\\",\\\"actNo\\\":\\\"6236682120001408033\\\",\\\"settleCycle\\\":\\\"T1\\\",\\\"endMonth\\\":\\\"01\\\",\\\"localFilePath\\\":\\\"/home/app/elecsignature/PDF/20230110/UMS000000398780185.pdf\\\"}', '00', '7.0', TIMESTAMP '2023-01-08 00:00:00.000000', TIMESTAMP '2024-01-09 00:00:00.000000');;\\nINSERT INTO UMS.T_UMS_ELEC_SIGNATURE_LOG\\n(UUID, ELEC_SIGNATURE_UUID, MNO, ELEC_SIGN_TYPE, FILE_NAME, USER_ALIAS, RESP_CODE, RESP_MSG, CREATE_TIME, UPDATE_TIME, RETRY_COUNT, PARAM_CONTENT, ELEC_CATEGORY, ELEC_VERSION, EFFECTIVE_DATE, EXPIRE_DATE)\\nVALUES('6c46358d2b594480b4e139b914e64791', '2cdb764814f4455da1f5e0c51befe0bb', '836871648165156', '00', 'UMS000001988611088.pdf', 'glcs20211116000722', '0000', '成功', TIMESTAMP '2023-01-10 16:48:36.000000', TIMESTAMP '2023-01-10 16:48:36.000000', 0, '{\\\"cprRegAddr\\\":\\\"江西省-赣州市-南康区-东山街道办事处河边街\\\",\\\"mecOnlineTyp\\\":\\\"线下\\\",\\\"fileName\\\":\\\"UMS000001988611088.pdf\\\",\\\"startMonth\\\":\\\"01\\\",\\\"debitRate\\\":\\\"0.55%\\\",\\\"legPerCrdTyp\\\":\\\"身份证\\\",\\\"jiaLinkMan\\\":\\\"谢启华\\\",\\\"signDate\\\":\\\"20230110\\\",\\\"endYear\\\":\\\"2024\\\",\\\"elecSignStart\\\":\\\"20230110\\\",\\\"jiaAccountNum\\\":\\\"6236682120001408033\\\",\\\"jiaName\\\":\\\"江西闽赣茶业有限公司\\\",\\\"ulWechatRate\\\":\\\"0.31%\\\",\\\"operLicNo\\\":\\\"360782210008986\\\",\\\"elecSignVersion\\\":\\\"7.0\\\",\\\"userAlias\\\":\\\"glcs20211116000722\\\",\\\"endDay\\\":\\\"09\\\",\\\"legPerCrdNo\\\":\\\"352229197712136510\\\",\\\"jiaMobile\\\":\\\"18514789854\\\",\\\"startTime\\\":\\\"20230110\\\",\\\"ulAlipayRate\\\":\\\"0.32%\\\",\\\"posPage\\\":\\\"11\\\",\\\"mecType\\\":\\\"企业\\\",\\\"cprRegNmCn\\\":\\\"江西闽赣茶业有限公司\\\",\\\"legPerNm\\\":\\\"谢启华\\\",\\\"jiaBankNum\\\":\\\"谢启华\\\",\\\"creditRate\\\":\\\"0.6%\\\",\\\"startDay\\\":\\\"10\\\",\\\"bnkNm\\\":\\\"中国建设银行\\\",\\\"startYear\\\":\\\"2023\\\",\\\"jiaBank\\\":\\\"中国建设银行\\\",\\\"operScope\\\":\\\"茶叶、预包装食品批发、零售；卷烟销售（依法须经批准的项目，经相关部门批准后方可开展经营活动）※\\\",\\\"busAddr\\\":\\\"宁夏回族自治区-银川市-金凤区-装机地址1号\\\",\\\"debitMaxFee\\\":\\\"25.0\\\",\\\"ulUnionRate\\\":\\\"\\\",\\\"posY\\\":\\\"425\\\",\\\"actNm\\\":\\\"谢启华\\\",\\\"actNo\\\":\\\"6236682120001408033\\\",\\\"settleCycle\\\":\\\"T1\\\",\\\"endMonth\\\":\\\"01\\\",\\\"localFilePath\\\":\\\"/home/app/elecsignature/PDF/20230110/UMS000001988611088.pdf\\\"}', '00', '7.0', TIMESTAMP '2023-01-10 00:00:00.000000', TIMESTAMP '2024-01-09 00:00:00.000000');;\\\"\\\"\\\"\\rif (this.metaClass.hasProperty(this, \\\"I18N_SUPPORT\\\") != null) { println(com.suixingpay.qa.autotest.core.util.I18nUtil.getGroovyString(\\\"groovy.ExecuteInitializationDataStart\\\")) } else { println(\\\">>>>>>>>>>>>>>>>>>>>>>执行初始化数据开始>>>>>>>>>>>>>>>>>>>>>>\\\") }\\r执行脚本(\\\"数据库_中台用户组数据源\\\",initScript0)\\r\\nif (this.metaClass.hasProperty(this, \\\"I18N_SUPPORT\\\") != null) { println(com.suixingpay.qa.autotest.core.util.I18nUtil.getGroovyString(\\\"groovy.ExecuteInitializationDataEnd\\\")) } else { println(\\\">>>>>>>>>>>>>>>>>>>>>>执行初始化数据结束>>>>>>>>>>>>>>>>>>>>>>\\\") }\\r\\n柜台::001_存在多份自主签署和静默签署_最新自主签署时间等于最新静默签署时间_最新自主签署创建时间大于最新静默签署创建时间_返回自主签署时间最新记录且标识为02{\\r\\n\\r  预期_INCLUDE \\\"返回结果断言\\\",\\\"\\\"\\\"{\\n  \\\"data\\\":{\\n    \\\"result\\\":[\\n      {\\n        \\\"elecSignType\\\":\\\"00\\\",\\n        \\\"resignType\\\":\\\"02\\\"\\n      }\\n    ]\\n  },\\n  \\\"rpMsg\\\":\\\"成功\\\",\\n  \\\"rpCd\\\":0\\n}\\\"\\\"\\\",\\\"0\\\"\\r}.req{\\r\\n\\r}.rsp{\\r\\n\\r}\\r\\n\\rif (this.metaClass.hasProperty(this, \\\"I18N_SUPPORT\\\") != null) { println(com.suixingpay.qa.autotest.core.util.I18nUtil.getGroovyString(\\\"groovy.startAssertRollback\\\")) } else { println(\\\">>>>>>>>>>>>>>>>>>>>>>执行断言回退数据开始>>>>>>>>>>>>>>>>>>>>>>\\\") }\\rif (this.metaClass.hasProperty(this, \\\"I18N_SUPPORT\\\") != null) { println(com.suixingpay.qa.autotest.core.util.I18nUtil.getGroovyString(\\\"groovy.endAssertRollback\\\")) } else { println(\\\">>>>>>>>>>>>>>>>>>>>>>执行断言回退数据结束>>>>>>>>>>>>>>>>>>>>>>\\\") }\"}";
        System.out.printf("%s", str.getBytes("utf-8").length);

        Map<String, String> map = new HashMap<>();

        map.put("a", "a");

        boolean b = map.values().stream().allMatch(v -> v.matches("a*"));

        Set<String> set = new HashSet<>();

        AtomicBoolean flag = new AtomicBoolean(true);
        map.forEach((k, v) -> {
            if (!v.matches("")) {
                System.out.println("key=" + k + ",value=" + v);
                flag.set(false);
            }
        });
        if (!flag.get()) return;

        map.values().stream().allMatch(v -> v.matches("xxx"));


        String keyFlag = map.keySet().stream().filter(key -> !map.get(key).matches("xxx")).findFirst().orElse("");
        String value = map.get(keyFlag);
        if (value == null || !value.matches("xxx")) {
            System.out.println("key=" + keyFlag + ",value" + map.get(keyFlag));
        }

    }
}
