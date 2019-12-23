package com.zui.test.iotest.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author zui
 * @description
 * @date 2019.12.23 8:49
 */
public class ChatNettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(new ChatNettyClientHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8090).sync();
            Channel channel = sync.channel();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String s = scanner.nextLine();
                channel.writeAndFlush(s);
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
