package com.zzh.boot3.io;

import cn.hutool.socket.nio.NioServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/24 15:32
 */
public class SocketChannelTest {

    @Test
    public void socketChannelTest() {
        try {
            // 打开 SocketChannel
            SocketChannel socketChannel = SocketChannel.open();


            // 连接远程服务器
            socketChannel.connect(new InetSocketAddress("12", 8080));
            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(buffer);
            // 写入数据
            String data = "Hello, Server!";
            buffer.clear();
            buffer.put(data.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }

            // 非阻塞模式
            socketChannel.configureBlocking(false);
            // 关闭通道
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @Test
    public void serverSocketChannelTest(){
        try {
            // 打开 ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定端口并设置非阻塞模式
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            // 接受连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(buffer);
            // 写入数据
            String data = "Hello, Client!";
            buffer.clear();
            buffer.put(data.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            // 关闭连接
            socketChannel.close();
            // 关闭 ServerSocketChannel
            serverSocketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void socketChannelSelectorTest(){
        try {
            // 打开 Selector
            Selector selector = Selector.open();

            // 将 Channel 注册到 Selector 上
//            channel1.register(selector, SelectionKey.OP_READ);
//            channel2.register(selector, SelectionKey.OP_WRITE);

            while (true) {
                // 轮询事件
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()) {
                        // 处理接受连接事件
                    } else if (key.isReadable()) {
                        // 处理读事件
                    } else if (key.isWritable()) {
                        // 处理写事件
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void nioServer(){
        NioServer nioServer=new NioServer(8080);
//        Selector selector = nioServer.getSelector();

    }
}
