package com.zzh.spring.aspect;

/**
 * @Description: 切面
 * @Author: zzh
 * @Crete 2023/3/21 18:08
 */
public class AspectDemo {

    /**
     *
     * @RestControllerAdvice + ResponseBodyAdvice : 对返回对象统一包装，ResponseBodyAdvice提供了一种全局的、可扩展的方式，用于对Controller处理方法返回的响应体进行统一处理。
     *
     * @ControllerAdvice +  RequestBodyAdviceAdapter :对请求对象统一包装
     *
     * 自定义注解+ @Aspect + @Around + @Around("@annotation(hashAnnotation)") ：实现对类的切面处理
     *   @Around("@annotation(hashAnnotation)")
     *   public Object doCacheBatchRemoveOperation(ProceedingJoinPoint invocation, CacheHash hashAnnotation) throws Throwable {
     *         return cacheHashHandler.proceed(invocation, hashAnnotation);
     *    }
     * */
    public static void main(String[] args) {

    }
}
