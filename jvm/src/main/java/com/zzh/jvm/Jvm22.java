package com.zzh.jvm;

/**
 * @Description: 对象创建的过程，对象的内存布局
 * @Author: zzh
 * @Crete 2023/2/4 17:30
 */
public class Jvm22 {

    //对象创建的过程：
    // 加载类元信息，对象分配内存，处理并发问题，属性默认初始化，设置对象头信息，属性的显示初始化（代码块中初始化，构造器中初始化）

    /**
     * 对象的内存布局：
     * 对象头：
     *     运行时元数据（Mark work）:
     *          哈希值
     *          GC分代年龄
     *          锁状态标志
     *          线程持有的锁
     *          偏向线程Id
     *          偏向时间戳
     *     类型指针，八个字节
     * 实例数据
     * 对齐填充
     * */
    public static void main(String[] args) {

    }
}
