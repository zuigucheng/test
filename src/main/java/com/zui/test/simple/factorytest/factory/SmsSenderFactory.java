package com.zui.test.simple.factorytest.factory;

import com.zui.test.simple.factorytest.sender.Sender;
import com.zui.test.simple.factorytest.sender.SmsSender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 10:52
 */
public class SmsSenderFactory implements Factory {
    public Sender getSender() {
        return new SmsSender();
    }
}
