package com.zui.test.iotest.netty.spiltpacket.handler;

import com.zui.test.iotest.netty.spiltpacket.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zui
 * @description
 * @date 2019.12.23 10:26
 */
public class MessageClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    public static final int MESSAGE_COUNT = 200;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "hello";
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(msg.getBytes(CharsetUtil.UTF_8).length);
            messageProtocol.setContent(msg.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

    }
}
