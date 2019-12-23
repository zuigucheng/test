package com.zui.test.simple.factorytest.factory;

import com.zui.test.simple.factorytest.sender.MailSender;
import com.zui.test.simple.factorytest.sender.Sender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 11:00
 */
public class MailSenderFactory implements Factory {
    public Sender getSender() {
        MailSender mailSender = new MailSender();
        return new MailSender();
    }
}
