package com.zui.test.simple.factorytest.sender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 10:32
 */
public class SmsSender implements Sender {
    @Override
    public void getMessage() {
        System.out.println("this is a sms's sender");
    }

}
