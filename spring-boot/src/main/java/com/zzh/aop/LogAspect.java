package com.zzh.aop;

import cn.hutool.json.JSONUtil;
import com.zzh.annotation.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.zzh.annotation.Logger)")
    public void loggerPoint() {
    }

    @Around("loggerPoint()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        System.out.println(JSONUtil.toJsonStr(signature));
        System.out.println(JSONUtil.toJsonStr(method.toString()));
        Logger logAnnotation = method.getAnnotation(Logger.class);
        if (logAnnotation != null) {
            // 注解上的描述
//            sysLog.setOperation(logAnnotation.value());
            System.out.println("日志value：" + logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
//        sysLog.setMethod(className + "." + methodName + "()");
        System.out.println("className is :" + className);
        System.out.println("methodName is :" + methodName);
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        System.out.println("args is :" + Arrays.toString(args));
        System.out.println(JSONUtil.toJsonStr(joinPoint));
        // 请求的方法参数名称
//        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
//        String[] paramNames = u.getParameterNames(method);
//        if (args != null && paramNames != null) {
//            String params = "";
//            for (int i = 0; i < args.length; i++) {
//                params += "  " + paramNames[i] + ": " + args[i];
//            }
//            sysLog.setParams(params);
//        }

    }
}
