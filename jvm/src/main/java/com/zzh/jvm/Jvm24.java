package com.zzh.jvm;

/**
 * @Description: 执行引擎
 * @Author: zzh
 * @Crete 2023/2/4 18:52
 */
public class Jvm24 {

    // 解析器和即时编译器

    // 解析器： 响应快，逐行进行翻译执行
    // 即时编译器：将整个函数解释成机器指令，并缓存起来，等到执行的时候直接执行机器指令就可以，执行效率高
    // 检测高频的热点代码，解释并缓存
    // hotSport虚拟机有解释器和即使编译器两种
    // 冷状态的虚拟机状态无法承受热状态服务器的流量
    // JIT 编译的条件：当一个方法（或者一个循环体）在一段时间内使用多次，就会触发即时编译

    // JIT编译器的种类：C1和C2
    public static void main(String[] args) {

    }
}
