package com.zui.test.simple.factorytest;

import com.zui.test.simple.factorytest.factory.Factory;
import com.zui.test.simple.factorytest.factory.MailSenderFactory;
import com.zui.test.simple.factorytest.factory.SmsSenderFactory;
import com.zui.test.simple.factorytest.sender.Sender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 10:31
 */
public class Test {
    public static void main(String[] args) {
        //xSender
        Factory senderFactory = new MailSenderFactory();
        Sender mailSender = senderFactory.getSender();

        senderFactory = new SmsSenderFactory();
        Sender sender = senderFactory.getSender();
    }


}
