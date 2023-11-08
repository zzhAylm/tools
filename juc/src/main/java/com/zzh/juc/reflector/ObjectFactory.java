//package com.zzh.juc.reflector;
//
//import javax.management.ReflectionException;
//import java.io.Serializable;
//import java.lang.reflect.Constructor;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
///**
// * MyBatis uses an ObjectFactory to create all needed new Objects.
// */
//public interface ObjectFactory {
//
//  /**
//   * Sets configuration properties.
//   */
//  default void setProperties(Properties properties) {
//    // NOP
//  }
//
//  /**
//   * Creates a new object with default constructor.
//   */
//  <T> T create(Class<T> type);
//
//  /**
//   * Creates a new object with the specified constructor and params.
//   */
//  <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);
//
//  /**
//   * Returns true if this object can have a set of other objects.
//   * It's main purpose is to support non-java.util.Collection objects like Scala collections.
//   */
//  <T> boolean isCollection(Class<T> type);
//
//}
//
///**
// * ObjectFactory接口 的唯一直接实现，反射工厂，根据传入的参数列表，选择
// * 合适的构造函数实例化对象，不传参数，则直接调用其无参构造方法
// */
//public class DefaultObjectFactory implements ObjectFactory, Serializable {
//
//  private static final long serialVersionUID = -8855120656740914948L;
//
//  @Override
//  public <T> T create(Class<T> type) {
//    return create(type, null, null);
//  }
//
//  @SuppressWarnings("unchecked")
//  @Override
//  public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
//    Class<?> classToCreate = resolveInterface(type);
//    // we know types are assignable
//    return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
//  }
//
//  @Override
//  public <T> boolean isCollection(Class<T> type) {
//    return false;
//  }
//
//  /**
//   * 通过反射来实例化给定的类，如果调用无参构造方法，则直接 constructor.newInstance()
//   * 如果有参，则根据参数类型和参数值进行调用
//   */
//  private  <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
//    try {
//      Constructor<T> constructor;
//      if (constructorArgTypes == null || constructorArgs == null) {
//        constructor = type.getDeclaredConstructor();
//        try {
//          return constructor.newInstance();
//        } catch (IllegalAccessException e) {
//          if (Reflector.canControlMemberAccessible()) {
//            constructor.setAccessible(true);
//            return constructor.newInstance();
//          } else {
//            throw e;
//          }
//        }
//      }
//      constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
//      try {
//        return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
//      } catch (IllegalAccessException e) {
//        if (Reflector.canControlMemberAccessible()) {
//          constructor.setAccessible(true);
//          return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
//        } else {
//          throw e;
//        }
//      }
//    } catch (Exception e) {
//      String argTypes = Optional.ofNullable(constructorArgTypes).orElseGet(Collections::emptyList)
//          .stream().map(Class::getSimpleName).collect(Collectors.joining(","));
//      String argValues = Optional.ofNullable(constructorArgs).orElseGet(Collections::emptyList)
//          .stream().map(String::valueOf).collect(Collectors.joining(","));
//      throw new ReflectionException("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + e, e);
//    }
//  }
//}
