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

    /**
     * Spring 循环依赖问题：
     * Spring只是解决了单例模式下属性依赖的循环问题；Spring为了解决单例的循环依赖问题，使用了三级缓存。
     *
     * 第一层缓存（singletonObjects）：单例对象缓存池，已经实例化并且属性赋值，这里的对象是成熟对象；
     * 第二层缓存（earlySingletonObjects）：单例对象缓存池，已经实例化但尚未属性赋值，这里的对象是半成品对象；
     * 第三层缓存（singletonFactories）: 单例工厂的缓存
     *
     * 分析getSingleton()的整个过程，Spring首先从一级缓存singletonObjects中获取。若是获取不到，而且对象正在建立中，就再从二级缓存earlySingletonObjects中获取。
     * 若是仍是获取不到且容许singletonFactories经过getObject()获取，就从三级缓存singletonFactory.getObject()(三级缓存)获取，若是获取到了则从三级缓存移动到了二级缓存。
     *
     * 此处就是解决循环依赖的关键，这段代码发生在createBeanInstance以后，也就是说单例对象此时已经被建立出来的。这个对象已经被生产出来了，
     * 虽然还不完美（尚未进行初始化的第二步和第三步），可是已经能被人认出来了（根据对象引用能定位到堆中的对象），因此Spring此时将这个对象提早曝光出来让你们认识，让你们使用。
     *
     * */
    /**
     * SpringMVC:
     *
     * DispatcherServlet :统一的入口 转发给HandlerMapping
     * HandlerMapping ： 包装请求 （包含一 个Handler 处理器（页面控制器）对象、多个HandlerInterceptor 拦截器）
     * HandlerAdapter ：   将会把处理器包装为适配器
     * 处理器（controller）:处理请求
     *
     * HandlerMapping是映射处理器
     * HandlerAdapter是处理适配器，它用来找到你的Controller中的处理方法
     * HandlerExceptionResolver是当遇到处理异常时的异常解析器
     * */


    public static void main(String[] args) {

    }
}
