//package com.zzh.juc.reflector;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public interface ReflectorFactory {
//
//  boolean isClassCacheEnabled();
//
//  void setClassCacheEnabled(boolean classCacheEnabled);
//
//  /**
//   * 主要看一下这个方法，通过 JavaBean 的 clazz 获取该 JavaBean 对应的 Reflector
//   */
//  Reflector findForClass(Class<?> type);
//}
//
///**
// * 支持定制化 ReflectorFactory
// */
//public class CustomReflectorFactory extends DefaultReflectorFactory {
//
//}
