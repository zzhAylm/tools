//package com.zzh.juc.reflector;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class DefaultReflectorFactory implements ReflectorFactory {
//    private boolean classCacheEnabled = true;
//
//    /**
//     * 大部分容器及工厂设计模式的管用伎俩，key：JavaBean的clazz，value：JavaBean对应的Reflector实例
//     */
//    private final ConcurrentMap<Class<?>, Reflector> reflectorMap = new ConcurrentHashMap<>();
//
//    /**
//     * 实例化一个 ConcurrentMap全局变量，然后暴露一个方法从 map 中获取目标对象，这种设计是很多框架都会用的
//     */
//    @Override
//    public Reflector findForClass(Class<?> type) {
//        if (classCacheEnabled) {
//            // synchronized (type) removed see issue #461
//            return reflectorMap.computeIfAbsent(type, Reflector::new);
//        } else {
//            return new Reflector(type);
//        }
//    }
//
//    public DefaultReflectorFactory() {
//    }
//
//    @Override
//    public boolean isClassCacheEnabled() {
//        return classCacheEnabled;
//    }
//
//    @Override
//    public void setClassCacheEnabled(boolean classCacheEnabled) {
//        this.classCacheEnabled = classCacheEnabled;
//    }
//}
