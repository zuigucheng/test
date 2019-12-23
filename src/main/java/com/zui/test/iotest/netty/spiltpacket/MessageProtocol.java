package com.zui.test.iotest.netty.spiltpacket;

import lombok.Data;

/**
 * @author zui
 * @description
 * @date 2019.12.23 10:27
 */
@Data
public class MessageProtocol {
    private int len;
    private byte[] content;
}
