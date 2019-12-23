package com.zui.test.simple.factorytest.sender;

/**
 * @author zui
 * @description
 * @date 2019.12.21 10:33
 */
public class MailSender implements Sender {
    @Override
    public void getMessage() {
        System.out.println("this is a mail's sender");
    }

    public void s() {

    }
}
