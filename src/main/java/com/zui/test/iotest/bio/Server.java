package com.zui.test.iotest.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zui
 * @date 2019.12.15 22:36
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("等待连接");

            //等待连接时阻塞
            Socket socket = serverSocket.accept();
            System.out.println("连接已创建");
//            new Thread(() -> {
//                try {
//                    handler(socket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
            handler(socket);
        }

    }

    private static void handler(Socket socket) throws IOException {
        System.out.println("thread id is:" + Thread.currentThread().getId());
        System.out.println("准备read");
        byte[] bytes = new byte[1024];

        //如果没有数据可读则阻塞
        int read = socket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println(new String(bytes, 0, read));
        }

        socket.getOutputStream().write("hello client".getBytes());
        socket.getOutputStream().flush();
    }
}
