package com.zui.test.iotest.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zui
 * @date 2019.12.15 22:36
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        socket.getOutputStream().write("hello server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("写数据结束");

        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);

        if (read != -1) {
            System.out.println(new String(bytes, 0, read));
        }
        socket.close();
    }
}
