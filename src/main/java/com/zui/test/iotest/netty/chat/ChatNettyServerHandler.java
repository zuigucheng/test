package com.zui.test.iotest.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zui
 * @description
 * @date 2019.12.23 8:49
 */
public class ChatNettyServerHandler extends SimpleChannelInboundHandler {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("========[ 客户端 ]:" + channel.remoteAddress() + "上线了==========");
        channelGroup.add(channel);
        System.out.println("========[ 客户端 ]:" + channel.remoteAddress() + "上线了==========");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("========[ 客户端 ]:" + channel.remoteAddress() + "下线了==========");
        System.out.println("========[ 客户端 ]:" + channel.remoteAddress() + "下线了==========");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[ 客户端 ]" + channel.remoteAddress() + "发送了消息:" + msg + "\n");
            } else {
                ch.writeAndFlush("[ 自己 ]" + channel.remoteAddress() + "发送了消息:" + msg + "\n");
            }
        });
    }
}
