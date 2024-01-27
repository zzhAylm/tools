package com.zzh.boot3.io;

import org.springframework.core.io.ClassPathResource;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {

    public static void main(String[] args) {
        try {
            // 打开一个 RandomAccessFile，以读写模式
            RandomAccessFile file = new RandomAccessFile(new ClassPathResource("static/test.txt").getFile(), "rw");
            FileChannel channel = file.getChannel();
            // 从文件中读取数据到缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            while (bytesRead != -1) {
                buffer.flip(); // 切换缓冲区为读取模式
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get()); // 逐个字节读取并输出
                }
                buffer.clear(); // 清空缓冲区
                bytesRead = channel.read(buffer);
            }
            // 将数据写入文件
            String data = "Hello, FileChannel!";
            buffer.clear();
            buffer.put(data.getBytes());
            buffer.flip(); // 切换缓冲区为写入模式
            channel.write(buffer);
            // 关闭通道和文件
            channel.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
