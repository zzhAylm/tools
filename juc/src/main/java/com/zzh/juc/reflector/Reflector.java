//package com.zzh.juc.reflector;
//
//import java.lang.reflect.Constructor;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//public class Reflector {
//
//  /** JavaBean 的 Class类型，在调用 Reflector 的构造方法时初始化该值 */
//  private final Class<?> type;
//
//  /** 可读的属性列表 */
//  private final String[] readablePropertyNames;
//  private final String[] writablePropertyNames;
//
//  /** key 属性名，value 该属性名对应的 setter方法调用器 */
//  private final Map<String, Invoker> setMethods = new HashMap<>();
//  private final Map<String, Invoker> getMethods = new HashMap<>();
//
//  /** key 属性名称，value 该属性 setter方法的返回值类型 */
//  private final Map<String, Class<?>> setTypes = new HashMap<>();
//  private final Map<String, Class<?>> getTypes = new HashMap<>();
//
//  /** type 的默认构造方法 */
//  private Constructor<?> defaultConstructor;
//
//  /** 所有属性名称的集合 */
//  private Map<String, String> caseInsensitivePropertyMap = new HashMap<>();
//
//  /**
//   * 里面的大部分方法都是通过简单的 JDK反射操作 实现的
//   * @param clazz
//   */
//  public Reflector(Class<?> clazz) {
//    type = clazz;
//    addDefaultConstructor(clazz);
//
//    // 处理 clazz 中的 所有getter方法，填充 getMethods集合 和 getTypes集合
//    addGetMethods(clazz);
//    addSetMethods(clazz);
//
//    // 处理没有 getter、setter方法 的字段
//    addFields(clazz);
//
//    // 根据 getMethods、setMethods集合 初始化可读、可写的属性
//    readablePropertyNames = getMethods.keySet().toArray(new String[0]);
//    writablePropertyNames = setMethods.keySet().toArray(new String[0]);
//
//    // 初始化 caseInsensitivePropertyMap集合，key 属性名的大写，value 属性名
//    for (String propName : readablePropertyNames) {
//      caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
//    }
//    for (String propName : writablePropertyNames) {
//      caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
//    }
//  }
//}
