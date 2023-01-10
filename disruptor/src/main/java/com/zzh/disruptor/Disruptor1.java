package com.zzh.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/10 14:05
 */
public class Disruptor1 {

    /**
     * 回到Disruptor，在我讲生产者时讲过ClaimStrategy。在这些代码中，你可以看见两个策略，
     * 一个是SingleThreadedStrategy（单线程策略）另一个是MultiThreadedStrategy（多线程策略）。
     * 你可能会有疑问，为什么在只有单个生产者时不用多线程的那个策略？它是否能够处理这种场景？当然可以。
     * 但多线程的那个使用了AtomicLong（Java提供的CAS操作），而单线程的使用long，没有锁也没有CAS。
     * 这意味着单线程版本会非常快，因为它只有一个生产者，不会产生序号上的冲突。
     *
     * 这就是我们所说的“分离竞争点问题”或者队列的“合并竞争点问题”。通过将所有的东西都赋予私有的序列号，
     * 并且只允许一个消费者写Entry对象中的变量来消除竞争，Disruptor 唯一需要处理访问冲突的地方，是多个生产者写入 Ring Buffer 的场景。
     *
     * 伪共享问题：
     * 设想你的消费者更新了head的值。缓存中的值和内存中的值都被更新了，而其他所有存储head的缓存行都会都会失效，因为其它缓存中head不是最新值了。
     * 请记住我们必须以整个缓存行作为单位来处理（译注：这是CPU的实现所规定的，详细可参见深入分析Volatile的实现原理），不能只把head标记为无效。
     *
     *
     * disruptor，通过填充变量消除了为共享问题：使disruptor一个变量占用一个缓存行，
     * 你会看到Disruptor消除这个问题，至少对于缓存行大小是64字节或更少的处理器架构来说是这样的（译注：有可能处理器的缓存行是128字节，那么使用64字节填充还是会存在伪共享问题），
     * 通过增加补全来确保ring buffer的序列号不会和其他东西同时存在于一个缓存行中。
     *
     *
     * 缓存行的缺点：缓存行中64个字节，会后多个数据（8个long类型的数据），如果着八个数据密切相关，那么我们读取进缓存中，可以增加命中率，但是如果我们修改了其中一个，另外7个都会失效，
     * 如果这是有线程命中其中的缓存数据，是无法使用的，因为缓存行中一个数据失效，会导致其他所有缓存行中的数据都失效
     *
     * 缓存系统中是以缓存行（cache line）为单位存储的。缓存行是2的整数幂个连续字节，一般为32-256个字节。最常见的缓存行大小是64个字节。当多线程修改互相独立的变量时，
     * 如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。缓存行上的写竞争是运行在SMP系统中并行线程实现可伸缩性最重要的限制因素。有人将伪共享描述成无声的性能杀手，
     * 因为从代码中很难看清楚是否会出现伪共享。
     *
     * 为每一个entry都赋予序列号，消费者就没有竞争，至于生产者端竞争
     * 为每个缓存行都填充变量，解决伪共享问题，
     *
     * 内存屏障是CPU指令，它允许你对数据什么时候对其他进程可见作出假设。在Java里，你使用volatile关键字来实现内存屏障。使用volatile意味着你不用被迫选择加锁，
     * 并且还能让你获得性能的提升。但是，你需要对你的设计进行一些更细致的思考，特别是你对volatile字段的使用有多频繁，以及对它们的读写有多频繁。
     *
     */
    public static void main(String[] args) {

        Disruptor<String> disruptor= new Disruptor<>(() -> null, 1024, Executors.defaultThreadFactory());

    }
}
