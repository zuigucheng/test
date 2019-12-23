package com.zui.test.iotest.netty.spiltpacket.handler;

import com.zui.test.iotest.netty.spiltpacket.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zui
 * @description
 * @date 2019.12.23 10:35
 */
public class MessageDecoder extends ByteToMessageDecoder {

    public static final int COUNT_LEN = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.isReadable(COUNT_LEN)) {
            int i = byteBuf.readInt();
            byte[] bytes = new byte[i];
            byteBuf.readBytes(bytes);

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(i);
            messageProtocol.setContent(bytes);

            list.add(messageProtocol);
        }
    }
}
