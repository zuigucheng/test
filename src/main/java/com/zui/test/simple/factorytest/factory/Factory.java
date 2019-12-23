package com.zui.test.simple.factorytest.factory;

import com.zui.test.simple.factorytest.sender.Sender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 11:01
 */
public interface Factory {
    Sender getSender();
}
