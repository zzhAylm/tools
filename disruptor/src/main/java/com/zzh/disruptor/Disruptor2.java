package com.zzh.disruptor;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @Description: RingBuffer
 * @Author: zzh
 * @Crete 2023/1/10 15:08
 */
public class Disruptor2 {


    //Disruptor: RingBuffer: 唤醒队列，序列号%bufferSize= index  ringBuffer[index]=定位数据
    //听起来，环形buffer非常适合这个场景。它维护了一个指向尾部的序号，当收到nak(校对注：拒绝应答信号)请求，可以重发从那一点到当前序号之间的所有消息：

    /**
     * ringBuffer的优点：
     * 之所以ringbuffer采用这种数据结构，是因为它在可靠消息传递方面有很好的性能。这就够了，不过它还有一些其他的优点。
     * 首先，因为它是数组，所以要比链表快，而且有一个容易预测的访问模式。（译者注：数组内元素的内存地址的连续性存储的）。
     * 这是对CPU缓存友好的—也就是说，在硬件级别，数组中的元素是会被预加载的，因此在ringbuffer当中，cpu无需时不时去主存加载数组中的下一个元素。
     * （校对注：因为只要一个元素被加载到缓存行，其他相邻的几个元素也会被加载进同一个缓存行）
     * 其次，你可以为数组预先分配内存，使得数组对象一直存在（除非程序终止）。这就意味着不需要花大量的时间用于垃圾回收。此外，不像链表那样，
     * 需要为每一个添加到其上面的对象创造节点对象—对应的，当删除节点时，需要执行相应的内存清理操作
     *
     * **/
    public static void main(String[] args) {
        Disruptor<String> disruptor= new Disruptor<>(() -> null, 1024, Executors.defaultThreadFactory());
    }
}
