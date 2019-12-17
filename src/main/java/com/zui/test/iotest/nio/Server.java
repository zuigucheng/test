package com.zui.test.iotest.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zui
 * @date 2019.12.15 22:51
 */
public class Server {

    /**
     * 1.创建通道
     * 2.创建复用器
     * 3.将通道注册到复用器上
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //设置阻塞模式
        serverSocketChannel.configureBlocking(false);

        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8080));

        //创建复用器
        Selector selector = Selector.open();

        //注册通道
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            System.out.println("等待事件发生");

            //监听事件,阻塞
            selector.select();

            System.out.println("监听到了事件");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handler(key);
            }
        }
    }

    private static void handler(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("连接事件发生");
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);

        } else if (key.isReadable()) {
            System.out.println("读取事件发生");
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();

            int read = channel.read(buffer);

            if (read != -1) {
                System.out.println(new String(buffer.array(), 0, read));
            }

            ByteBuffer wrap = ByteBuffer.wrap("hello client".getBytes());
            channel.write(wrap);

            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            channel.close();
        }
    }
}

