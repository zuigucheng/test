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
public class MessageServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        System.out.println(++count + "号数据包:" + new String(msg.getContent(), CharsetUtil.UTF_8));
    }
}
