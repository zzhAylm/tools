package com.zzh.spring;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/11 20:57
 */
public class Spring1 {

    //SPI技术：
    /**
     * 程序运行过程中，使用 ServiceLoader 动态加载某个接口的实现类，并可以调用重写的方法
     * SPI 技术是一种扩展机制，用于在应用程序运行时动态地发现和加载类的实现，因此应该谨慎使用，避免过度使用导致代码难以维护。
     * 定义一个接口：在应用程序中定义一个接口，这个接口可以有多个不同的实现。
     *
     * 创建一个实现类：实现该接口的类，并在类的声明中使用 @Service 或 @Provider 等注解将其标记为可供发现的实现类。
     *
     * 配置文件：在应用程序的 classpath 下创建一个文件夹 META-INF/services/，并在其中创建一个以接口全限定名命名的文本文件，例如 com.example.MyInterface。在这个文本文件中，列出了该接口的所有实现类的全限定名，每行一个。
     *
     * 加载实现类：在应用程序启动时，使用 ServiceLoader 类的 load 方法加载接口的所有实现类，并返回一个 Iterable 对象。通过迭代这个对象，就可以动态地发现和加载类的实现。
     *
     *
     * IOC:控制反转
     * Spring框架管理这些Bean的创建工作，即由用户管理Bean转变为框架管理Bean，这个就叫控制反转 - Inversion of Control (IoC)
     * Spring 框架托管创建的Bean放在哪里呢？这便是IoC Container;
     * Spring 框架为了更好让用户配置Bean，必然会引入不同方式来配置Bean？ 这便是xml配置，Java配置，注解配置等
     * 支持Spring 框架既然接管了Bean的生成，必然需要管理整个Bean的生命周期等；
     * 应用程序代码从Ioc Container中获取依赖的Bean，注入到应用程序中，这个过程叫 依赖注入(Dependency Injection，DI) ；所以说控制反转是通过依赖注入实现的，其实它们是同一个概念的不同角度描述。通俗来说就是IoC是设计思想，DI是实现方式
     * 在依赖注入时，有哪些方式呢？这就是构造器方式，@Autowired, @Resource, @Qualifier... 同时Bean之间存在依赖（可能存在先后顺序问题，以及循环依赖问题等）
     *
     *
     * AOP：
     * Spring 框架通过定义切面, 通过拦截切点实现了不同业务模块的解耦，这个就叫面向切面编程 - Aspect Oriented Programming (AOP)
     * 为什么@Aspect注解使用的是aspectj的jar包呢？这就引出了Aspect4J和Spring AOP的历史渊源，只有理解了Aspect4J和Spring的渊源才能理解有些注解上的兼容设计
     * 如何支持更多拦截方式来实现解耦， 以满足更多场景需求呢？ 这就是@Around, @Pointcut... 等的设计
     * 那么Spring框架又是如何实现AOP的呢？ 这就引入代理技术，分静态代理和动态代理，动态代理又包含JDK代理和CGLIB代理等
     *
     * 例如，execution(public * com.example.service.*.*(..))表示匹配com.example.service包下所有public方法。这个表达式可以分为以下几部分：
     *
     * execution：表示匹配方法的执行。
     * public：表示方法的修饰符为public。
     * *：表示方法的返回类型可以是任意类型。
     * com.example.service.*：表示方法的声明类型为com.example.service包下的任意类。
     * *：表示方法名可以是任意名称。
     * (..)：表示方法的参数可以是任意类型和数量。
     *
     * AspectJ是一个java实现的AOP框架
     * */
    public static void main(String[] args) {

    }
}
