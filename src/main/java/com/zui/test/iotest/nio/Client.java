package com.zui.test.iotest.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zui
 * @date 2019.12.15 23:15
 */
public class Client {

    private Selector selector;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initClient("127.0.0.1", 8080);
        client.connect();
    }

    private void initClient(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        this.selector = Selector.open();

        channel.connect(new InetSocketAddress(ip, port));

        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void connect() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    ByteBuffer wrap = ByteBuffer.wrap("hello server".getBytes());
                    channel.write(wrap);

                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    read(key);
                }
                iterator.remove();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer allocate = ByteBuffer.allocate(512);
        int read = channel.read(allocate);

        if (read != -1) {
            System.out.println(new String(allocate.array(), 0, read));
        }
    }
}

